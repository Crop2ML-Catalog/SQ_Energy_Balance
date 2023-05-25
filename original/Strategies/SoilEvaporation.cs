
using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;    
using SiriusQualityEnergyBalance.DomainClass;
namespace SiriusQualityEnergyBalance.Strategies
{
    public class SoilEvaporation
    {
        public void Estimate(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            try
            {
                CalculateModel(s, s1, r, a, ex);
            }
            catch (Exception exception)
            {
                string msg = "Error in component SiriusQualityEnergyBalance, strategy: " + this.GetType().Name + ": Unhandled exception running model. "+exception.GetType().FullName+" - "+exception.Message;
                throw new Exception(msg, exception);
            }
        }

        
        public SoilEvaporation() { }
    
        private void CalculateModel(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
                //- Name: SoilEvaporation -Version: 1.0, -Time step: 1
                //- Description:
    //            * Title: SoilEvaporation Model
    //            * Authors: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: Starting from a soil at field capacity, soil evaporation  is assumed to
    //                be energy limited during the first phase of evaporation and diffusion limited thereafter.
    //                Hence, the soil evaporation model considers these two processes taking the minimum between
    //                the energy limited evaporation (PtSoil) and the diffused limited
    //                evaporation 
    //            * ShortDescription: Starting from a soil at field capacity, soil evaporation  is assumed to
    //            be energy limited during the first phase of evaporation and diffusion limited thereafter.
    //            Hence, the soil evaporation model considers these two processes taking the minimum between
    //            the energy limited evaporation (PtSoil) and the diffused limited
    //            evaporation
                //- inputs:
    //            * name: diffusionLimitedEvaporation
    //                          ** description : diffusion Limited Evaporation
    //                          ** variablecategory : state
    //                          ** datatype : DOUBLE
    //                          ** default : 6605.505
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: energyLimitedEvaporation
    //                          ** description : energy Limited Evaporation
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** default : 448.240
    //                          ** min : 0
    //                          ** max : 1000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
                //- outputs:
    //            * name: soilEvaporation
    //                          ** description : soil Evaporation
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 5000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
            double diffusionLimitedEvaporation = s.diffusionLimitedEvaporation;
            double energyLimitedEvaporation = a.energyLimitedEvaporation;
            double soilEvaporation;
            soilEvaporation = Math.Min(diffusionLimitedEvaporation, energyLimitedEvaporation);
            a.soilEvaporation= soilEvaporation;
        }

    }
}