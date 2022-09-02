using System;
using System.Collections.Generic;
using System.Linq;
public class DiffusionLimitedEvaporation
{
    private double _soilDiffusionConstant;
    public double soilDiffusionConstant
        {
            get { return this._soilDiffusionConstant; }
            set { this._soilDiffusionConstant= value; } 
        }
    public DiffusionLimitedEvaporation() { }
    
    public void  CalculateModel(EnergyBalanceState s, EnergyBalanceState s1, EnergyBalanceRate r, EnergyBalanceAuxiliary a, EnergyBalanceExogenous ex)
    {
        //- Name: DiffusionLimitedEvaporation -Version: 1.0, -Time step: 1
        //- Description:
    //            * Title: DiffusionLimitedEvaporation Model
    //            * Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: the evaporation from the diffusion limited soil 
    //            * ShortDescription: It calculates the diffusion limited evaropration
    //        
        //- inputs:
    //            * name: deficitOnTopLayers
    //                          ** description : deficit On TopLayers
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** default : 5341
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: soilDiffusionConstant
    //                          ** description : soil Diffusion Constant
    //                          ** parametercategory : soil
    //                          ** datatype : DOUBLE
    //                          ** default : 4.2
    //                          ** min : 0
    //                          ** max : 10
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
        //- outputs:
    //            * name: diffusionLimitedEvaporation
    //                          ** description : the evaporation from the diffusion limited soil 
    //                          ** variablecategory : state
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 5000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
        double deficitOnTopLayers = a.deficitOnTopLayers;
        double diffusionLimitedEvaporation;
        if (deficitOnTopLayers / 1000.0d <= 0.0d)
        {
            diffusionLimitedEvaporation = 8.3d * 1000.0d;
        }
        else
        {
            if (deficitOnTopLayers / 1000.0d < 25.0d)
            {
                diffusionLimitedEvaporation = 2.0d * soilDiffusionConstant * soilDiffusionConstant / (deficitOnTopLayers / 1000.0d) * 1000.0d;
            }
            else
            {
                diffusionLimitedEvaporation = 0.0d;
            }
        }
        s.diffusionLimitedEvaporation= diffusionLimitedEvaporation;
    }
}