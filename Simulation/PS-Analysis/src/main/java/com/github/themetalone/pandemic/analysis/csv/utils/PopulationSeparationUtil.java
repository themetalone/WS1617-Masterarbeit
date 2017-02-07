package com.github.themetalone.pandemic.analysis.csv.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.analysis.csv.record.HealthStateStateRecord;
import com.github.themetalone.pandemic.analysis.csv.record.HealthStateStateRecordFactory;
import com.github.themetalone.pandemic.analysis.exception.HeaderException;

/**
 * @author Steffen
 *
 */
public class PopulationSeparationUtil {

  private static Logger LOG = LoggerFactory.getLogger("SeparationUtil");

  /**
   * Reads records of HealthStateStateType from a csv file. Simply a wrapper for the
   * {@link CSVParser#parse(File, java.nio.charset.Charset, CSVFormat)} method
   *
   * @param healthStateStatesCsv file containing csv data of the HealthStateStateType
   * @return Collection&lt;CSVRecord>
   * @throws IOException from CSVParser.parse()
   */
  public Collection<CSVRecord> readHealthStateStates(File healthStateStatesCsv) throws IOException {

    return CSVParser.parse(healthStateStatesCsv, StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();

  }

  /**
   * Separates the records by population
   *
   * @param records Collection&lt;CSVRecords> of HealthStateStateType CSV data
   * @return Map&lt;Integer, Collection&lt;HealthStateStateRecord>> with each Integer being a population id and the
   *         collection containing all data from that population
   */
  public Map<Integer, Collection<HealthStateStateRecord>> separateRecords(Collection<CSVRecord> records) {

    final Map<Integer, Collection<HealthStateStateRecord>> result = new HashMap<>();
    records.stream().map(csv -> {
      try {
        return new HealthStateStateRecordFactory().parse(csv);
      } catch (HeaderException e) {
        return null;
      }
    }).filter(hssr -> hssr != null).forEach(hsr -> {
      if (!result.containsKey(hsr.getPopId())) {
        result.put(hsr.getPopId(), new LinkedList<>());
      }
      result.get(hsr.getPopId()).add(hsr);
    });
    return result;
  }

  /**
   * Pretty prints the population data into the form<br/>
   * <table>
   * <th>
   * <td>'TICK'</td>
   * <td>HSID</td>
   * <td>HSID</td>
   * <td>HSID</td>
   * <td>...</td></th>
   * <tr>
   * <td>0</td>
   * <td>1000</td>
   * <td>5</td>
   * <td>0</td>
   * <td>...</td>
   * </tr>
   * <tr>
   * <td>1</td>
   * <td>995</td>
   * <td>10</td>
   * <td>5</td>
   * <td>...</td>
   * </tr>
   * <tr>
   * <td>...</td>
   * <td>...</td>
   * <td>...</td>
   * <td>...</td>
   * <td>...</td>
   * </tr>
   * </table>
   *
   * @param records records of a single population
   * @return csv printable List&lt;String[]> with header and the data
   */
  public List<String[]> makePopulation(Collection<HealthStateStateRecord> records) {

    List<String[]> result = new ArrayList<>(records.size());
    HealthStateStateRecord reference = records.stream().findFirst().get();
    final int numberOfStates = records.stream().filter(hssr -> hssr.getTick() == reference.getTick())
        .mapToInt(hssr -> hssr.getHsId()).max().getAsInt() + 1;
    String[] header = new String[numberOfStates + 1];
    header[0] = "'TICK'";
    for (int i = 1; i <= numberOfStates; i++) {
      header[i] = "'" + String.valueOf(i - 1) + "'";
    }
    result.add(header);
    records.stream().mapToLong(r -> r.getTick()).forEachOrdered(t -> {
      String[] entry = new String[numberOfStates + 1];
      entry[0] = String.valueOf(t);
      records.stream().filter(r -> r.getTick() == t).forEachOrdered(r -> {
        entry[r.getHsId() + 1] = String.valueOf(r.getSize());
      });
      result.add(entry);
    });
    return result;

  }

  /**
   * Removes tick duplicates from the data set
   *
   * @param data from {@link PopulationSeparationUtil#makePopulation(Collection)}
   * @return List&lt;String[]> with unique ticks
   */
  public List<String[]> cleanData(List<String[]> data) {

    final Collection<Long> ticks = new LinkedList<>();
    List<String[]> result = new LinkedList<>();
    result.add(data.get(0));
    // remove header
    data.remove(0);
    data.stream().sorted((s, t) -> Long.valueOf(s[0]).intValue() - Long.valueOf(t[0]).intValue()).filter(s -> {
      // a line is only added if no other line with the same tick has been added
      if (!ticks.contains(Long.valueOf(s[0]))) {
        ticks.add(Long.valueOf(s[0]));
        return true;
      }
      return false;
    }).forEachOrdered(result::add);

    return result;

  }

  /**
   * Writes the population data in separate files using the {@link #makePopulation(Collection)} method
   *
   * @param populationMap result of {@link #separateRecords(Collection)}
   * @param folder in which the files will be placed
   * @param prefix of the files. The files are named as: prefix + populationId + '.csv'
   */
  public void writeData(Map<Integer, Collection<HealthStateStateRecord>> populationMap, Path folder, String prefix) {

    folder.toFile().mkdirs();
    populationMap.forEach((k, v) -> {
      try {
        BufferedWriter bw =
            new BufferedWriter(new FileWriter(new File(folder.toFile(), prefix + k.toString() + ".csv")));
        CSVPrinter csvWriter = new CSVPrinter(bw, CSVFormat.EXCEL);
        csvWriter.printRecords(cleanData(makePopulation(v)));
        csvWriter.flush();
        csvWriter.close();
      } catch (IOException e) {
        throw new Error("Problems trying to access file " + folder.toString() + "/" + prefix + k.toString() + "csv", e);
      }
    });
  }

}
