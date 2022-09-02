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


public class SoilHeatFlux extends FWSimComponent
{
    private FWSimVariable<Double> netRadiationEquivalentEvaporation;
    private FWSimVariable<Double> tau;
    private FWSimVariable<Double> soilEvaporation;
    private FWSimVariable<Double> soilHeatFlux;

    public SoilHeatFlux(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public SoilHeatFlux(){
        super();
    }
    @Override
    protected void process()
    {
        double t_netRadiationEquivalentEvaporation = netRadiationEquivalentEvaporation.getValue();
        double t_tau = tau.getValue();
        double t_soilEvaporation = soilEvaporation.getValue();
        double t_soilHeatFlux;
        t_soilHeatFlux = t_tau * t_netRadiationEquivalentEvaporation - t_soilEvaporation;
        soilHeatFlux.setValue(t_soilHeatFlux, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(0.9983, iFieldMap.get("SoilHeatFlux.tau"), this);
            FWSimVariable.setValue(638.142, iFieldMap.get("SoilHeatFlux.netRadiationEquivalentEvaporation"), this);
            FWSimVariable.setValue(448.240, iFieldMap.get("SoilHeatFlux.soilEvaporation"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(188.817, iFieldMap.get("SoilHeatFlux.soilHeatFlux"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new SoilHeatFlux(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("netRadiationEquivalentEvaporation", "net Radiation Equivalent Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 5000, 638.142, this));
        addVariable(FWSimVariable.createSimVariable("tau", "plant cover factor", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 100, 0.9983, this));
        addVariable(FWSimVariable.createSimVariable("soilEvaporation", "soil Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 10000, 448.240, this));
        addVariable(FWSimVariable.createSimVariable("soilHeatFlux", "soil Heat Flux ", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, null, this));

        return iFieldMap;
    }
}