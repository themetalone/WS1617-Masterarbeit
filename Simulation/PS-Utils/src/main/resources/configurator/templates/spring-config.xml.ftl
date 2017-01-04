<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">



  <bean id="batchsize" class="java.lang.Long">
    <constructor-arg value="${batchsize}"/>
  </bean>
  <bean id="datalocation" class="java.lang.String">
    <constructor-arg value="${datalocation}"/>
  </bean>



  <bean name="Simulation" class="com.github.themetalone.pandemic.simulation.Simulation" id="simulation">
    <constructor-arg index="0" type="java.lang.Long" value="${simulationtime}"></constructor-arg>
  </bean>

  <bean id="hsprovider" name="HealthStateProvider" class="com.github.themetalone.pandemic.simulation.healthState.HealthStateProvider">
    <constructor-arg index="0">
      <list>
        ${healthstates}
      </list>
    </constructor-arg>
  </bean>
  <bean id="tprovider" name="TransmissionProvider" class="com.github.themetalone.pandemic.simulation.transmission.TransmissionProvider">
    <constructor-arg index="0">
      <list>
        ${transmissions}
      </list>
    </constructor-arg>
  </bean>
  <bean id="dwprovider" name="DataWriterProvider" class="com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider">
    <constructor-arg index="0">
      <bean id="dw" name="DataWriter" class="com.github.themetalone.pandemic.simulation.data.H2SqlPandemicSimulationDataWriter">
        <constructor-arg index="0">
          <bean id="sqlconnector" name="SQLConnector" clas="com.github.themetalone.pandemic.utils.data.H2SQLConnector">
            <constructor-arg ref="datalocation"></constructor-arg>
          </bean>
        </constructor-arg>
        <constructor-arg index="1" ref="batchsize"></constructor-arg>
      </bean>
    </constructor-arg>
  </bean>
</beans>
