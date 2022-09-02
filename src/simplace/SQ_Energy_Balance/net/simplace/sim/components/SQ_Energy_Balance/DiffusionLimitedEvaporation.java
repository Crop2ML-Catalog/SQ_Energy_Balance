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


public class DiffusionLimitedEvaporation extends FWSimComponent
{
    private FWSimVariable<Double> deficitOnTopLayers;
    private FWSimVariable<Double> soilDiffusionConstant;
    private FWSimVariable<Double> diffusionLimitedEvaporation;

    public DiffusionLimitedEvaporation(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public DiffusionLimitedEvaporation(){
        super();
    }
    @Override
    protected void process()
    {
        double t_deficitOnTopLayers = deficitOnTopLayers.getValue();
        double t_soilDiffusionConstant = soilDiffusionConstant.getValue();
        double t_diffusionLimitedEvaporation;
        if (t_deficitOnTopLayers / 1000.0d <= 0.0d)
        {
            t_diffusionLimitedEvaporation = 8.3d * 1000.0d;
        }
        else
        {
            if (t_deficitOnTopLayers / 1000.0d < 25.0d)
            {
                t_diffusionLimitedEvaporation = 2.0d * t_soilDiffusionConstant * t_soilDiffusionConstant / (t_deficitOnTopLayers / 1000.0d) * 1000.0d;
            }
            else
            {
                t_diffusionLimitedEvaporation = 0.0d;
            }
        }
        diffusionLimitedEvaporation.setValue(t_diffusionLimitedEvaporation, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(4.2, iFieldMap.get("DiffusionLimitedEvaporation.soilDiffusionConstant"), this);
            FWSimVariable.setValue(5341, iFieldMap.get("DiffusionLimitedEvaporation.deficitOnTopLayers"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(6605.505, iFieldMap.get("DiffusionLimitedEvaporation.diffusionLimitedEvaporation"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new DiffusionLimitedEvaporation(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("deficitOnTopLayers", "deficit On TopLayers", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 10000, 5341, this));
        addVariable(FWSimVariable.createSimVariable("soilDiffusionConstant", "soil Diffusion Constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 10, 4.2, this));
        addVariable(FWSimVariable.createSimVariable("diffusionLimitedEvaporation", "the evaporation from the diffusion limited soil ", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", 0, 5000, null, this));

        return iFieldMap;
    }
}