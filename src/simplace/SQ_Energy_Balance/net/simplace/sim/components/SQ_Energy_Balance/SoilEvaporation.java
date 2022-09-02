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


public class SoilEvaporation extends FWSimComponent
{
    private FWSimVariable<Double> diffusionLimitedEvaporation;
    private FWSimVariable<Double> energyLimitedEvaporation;
    private FWSimVariable<Double> soilEvaporation;

    public SoilEvaporation(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public SoilEvaporation(){
        super();
    }
    @Override
    protected void process()
    {
        double t_diffusionLimitedEvaporation = diffusionLimitedEvaporation.getValue();
        double t_energyLimitedEvaporation = energyLimitedEvaporation.getValue();
        double t_soilEvaporation;
        t_soilEvaporation = Math.min(t_diffusionLimitedEvaporation, t_energyLimitedEvaporation);
        soilEvaporation.setValue(t_soilEvaporation, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(6605.505, iFieldMap.get("SoilEvaporation.diffusionLimitedEvaporation"), this);
            FWSimVariable.setValue(448.240, iFieldMap.get("SoilEvaporation.energyLimitedEvaporation"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(448.240, iFieldMap.get("SoilEvaporation.soilEvaporation"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new SoilEvaporation(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("diffusionLimitedEvaporation", "diffusion Limited Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", 0, 10000, 6605.505, this));
        addVariable(FWSimVariable.createSimVariable("energyLimitedEvaporation", "energy Limited Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 448.240, this));
        addVariable(FWSimVariable.createSimVariable("soilEvaporation", "soil Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.out,"", 0, 5000, null, this));

        return iFieldMap;
    }
}