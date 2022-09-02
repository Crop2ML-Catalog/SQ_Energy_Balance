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


public class CropHeatFlux extends FWSimComponent
{
    private FWSimVariable<Double> netRadiationEquivalentEvaporation;
    private FWSimVariable<Double> soilHeatFlux;
    private FWSimVariable<Double> potentialTranspiration;
    private FWSimVariable<Double> cropHeatFlux;

    public CropHeatFlux(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public CropHeatFlux(){
        super();
    }
    @Override
    protected void process()
    {
        double t_netRadiationEquivalentEvaporation = netRadiationEquivalentEvaporation.getValue();
        double t_soilHeatFlux = soilHeatFlux.getValue();
        double t_potentialTranspiration = potentialTranspiration.getValue();
        double t_cropHeatFlux;
        t_cropHeatFlux = t_netRadiationEquivalentEvaporation - t_soilHeatFlux - t_potentialTranspiration;
        cropHeatFlux.setValue(t_cropHeatFlux, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(638.142, iFieldMap.get("CropHeatFlux.netRadiationEquivalentEvaporation"), this);
            FWSimVariable.setValue(188.817, iFieldMap.get("CropHeatFlux.soilHeatFlux"), this);
            FWSimVariable.setValue( 1.413, iFieldMap.get("CropHeatFlux.potentialTranspiration"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue( 447.912, iFieldMap.get("CropHeatFlux.cropHeatFlux"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new CropHeatFlux(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("netRadiationEquivalentEvaporation", "net Radiation Equivalent Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 10000, 638.142, this));
        addVariable(FWSimVariable.createSimVariable("soilHeatFlux", "soil Heat Flux", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 1000, 188.817, this));
        addVariable(FWSimVariable.createSimVariable("potentialTranspiration", "potential Transpiration", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 1000,  1.413, this));
        addVariable(FWSimVariable.createSimVariable("cropHeatFlux", " crop Heat Flux", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, null, this));

        return iFieldMap;
    }
}