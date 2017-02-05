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

import com.github.themetalone.pandemic.analysis.csv.record.HealthStateStateRecord;
import com.github.themetalone.pandemic.analysis.csv.record.HealthStateStateRecordFactory;
import com.github.themetalone.pandemic.analysis.exception.HeaderException;

/**
 * @author Steffen
 *
 */
public class PopulationSeparationUtil {

  public Collection<CSVRecord> readHealthStateStates(File healthStateStatesCsv) throws IOException {

    return CSVParser.parse(healthStateStatesCsv, StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();

  }

  public Map<Integer, Collection<HealthStateStateRecord>> separateRecords(Collection<CSVRecord> records) {

    final Map<Integer, Collection<HealthStateStateRecord>> result = new HashMap<>();
    records.stream().map(csv -> {
      try {
        return new HealthStateStateRecordFactory().parse(csv);
      } catch (HeaderException e) {
        throw new Error("Couldn't parse csv:" + csv.toString(), e);
      }
    }).forEach(hsr -> {
      if (!result.containsKey(hsr.getPopId())) {
        result.put(hsr.getPopId(), new LinkedList<>());
      }
      result.get(hsr.getPopId()).add(hsr);
    });
    return result;
  }

  public Collection<CSVRecord> makePopulation(Collection<HealthStateStateRecord> records) {

    List<String[]> result = new ArrayList<>(records.size());
    HealthStateStateRecord reference = records.stream().findFirst().get();
    int numberOfStates = records.stream().filter(hssr -> hssr.getTick() == reference.getTick())
        .mapToInt(hssr -> hssr.getHsId()).max().getAsInt();
    String[] header = new String[numberOfStates + 1];
    for (int i = 0; i <= numberOfStates; i++) {
      header[i] = "'" + String.valueOf(i) + "'";
    }
    result.add(header);

  }

  public void writeData(Map<Integer, Collection<HealthStateStateRecord>> populationMap, Path folder, String prefix) {

    folder.toFile().mkdirs();
    populationMap.forEach((k, v) -> {
      try {
        BufferedWriter bw =
            new BufferedWriter(new FileWriter(new File(folder.toFile(), prefix + k.toString() + ".csv")));
        CSVPrinter csvWriter = new CSVPrinter(bw, CSVFormat.EXCEL);
        v.stream().sorted().map(hsr -> hsr.toCsv()).forEach(t -> {
          try {
            csvWriter.printRecord(t);
          } catch (IOException e) {
            try {
              csvWriter.close();
            } catch (IOException e1) {
              throw new Error("Could not close file after a problem occured " + folder.toString() + "/" + prefix
                  + k.toString() + "csv", e);
            }
            throw new Error("Could not write to file " + folder.toString() + "/" + prefix + k.toString() + "csv", e);
          }
        });
        csvWriter.close();
      } catch (IOException e) {
        throw new Error("Problems trying to access file " + folder.toString() + "/" + prefix + k.toString() + "csv", e);
      }
    });
  }

}
