package net.simplace.sim.components.SQ_Energy_Balance;
import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import net.simplace.sim.model.FWSimComponent;
import net.simplace.sim.util.FWSimVarMap;
import net.simplace.sim.util.FWSimVariable;
import net.simplace.sim.util.FWSimVariable.CONTENT_TYPE;
import net.simplace.sim.util.FWSimVariable.DATA_TYPE;
import org.jdom2.Element;


public class Conductance extends FWSimComponent
{
    private FWSimVariable<Double> vonKarman;
    private FWSimVariable<Double> heightWeatherMeasurements;
    private FWSimVariable<Double> zm;
    private FWSimVariable<Double> zh;
    private FWSimVariable<Double> d;
    private FWSimVariable<Double> plantHeight;
    private FWSimVariable<Double> wind;
    private FWSimVariable<Double> conductance;

    public Conductance(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public Conductance(){
        super();
    }
    @Override
    protected void process()
    {
        double t_vonKarman = vonKarman.getValue();
        double t_heightWeatherMeasurements = heightWeatherMeasurements.getValue();
        double t_zm = zm.getValue();
        double t_zh = zh.getValue();
        double t_d = d.getValue();
        double t_plantHeight = plantHeight.getValue();
        double t_wind = wind.getValue();
        double t_conductance;
        double h;
        h = Math.max(10.0d, t_plantHeight) / 100.0d;
        t_conductance = t_wind * Math.pow(t_vonKarman, 2) / (Math.log((t_heightWeatherMeasurements - (t_d * h)) / (t_zm * h)) * Math.log((t_heightWeatherMeasurements - (t_d * h)) / (t_zh * h)));
        conductance.setValue(t_conductance, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(0.67, iFieldMap.get("Conductance.d"), this);
            FWSimVariable.setValue(0.013, iFieldMap.get("Conductance.zh"), this);
            FWSimVariable.setValue(0.13, iFieldMap.get("Conductance.zm"), this);
            FWSimVariable.setValue(124000, iFieldMap.get("Conductance.wind"), this);
            FWSimVariable.setValue(0, iFieldMap.get("Conductance.plantHeight"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(598.685, iFieldMap.get("Conductance.conductance"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new Conductance(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("vonKarman", "von Karman constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.42, this));
        addVariable(FWSimVariable.createSimVariable("heightWeatherMeasurements", "reference height of wind and humidity measurements", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 10, 2, this));
        addVariable(FWSimVariable.createSimVariable("zm", "roughness length governing momentum transfer, FAO", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.13, this));
        addVariable(FWSimVariable.createSimVariable("zh", "roughness length governing transfer of heat and vapour, FAO", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.013, this));
        addVariable(FWSimVariable.createSimVariable("d", "corresponding to 2/3. This is multiplied to the crop heigth for calculating the zero plane displacement height, FAO", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.67, this));
        addVariable(FWSimVariable.createSimVariable("plantHeight", "plant Height", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 0, this));
        addVariable(FWSimVariable.createSimVariable("wind", "wind", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000000, 124000, this));
        addVariable(FWSimVariable.createSimVariable("conductance", "the boundary layer conductance", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", 0, 10000, null, this));

        return iFieldMap;
    }
}