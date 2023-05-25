
using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;    
using SiriusQualityEnergyBalance.DomainClass;
namespace SiriusQualityEnergyBalance.Strategies
{
    public class PtSoil
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

        private double _Alpha;
        public double Alpha
        {
            get { return this._Alpha; }
            set { this._Alpha= value; } 
        }
        private double _tauAlpha;
        public double tauAlpha
        {
            get { return this._tauAlpha; }
            set { this._tauAlpha= value; } 
        }
        public PtSoil() { }
    
        private void CalculateModel(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
                //- Name: PtSoil -Version: 1.0, -Time step: 1
                //- Description:
    //            * Title: PtSoil EnergyLimitedEvaporation Model
    //            * Authors: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference: https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: Evaporation from the soil in the energy-limited stage 
    //            * ShortDescription: Evaporation from the soil in the energy-limited stage
                //- inputs:
    //            * name: evapoTranspirationPriestlyTaylor
    //                          ** description : evapoTranspiration Priestly Taylor
    //                          ** variablecategory : rate
    //                          ** datatype : DOUBLE
    //                          ** default : 120
    //                          ** min : 0
    //                          ** max : 1000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: Alpha
    //                          ** description : Priestley-Taylor evapotranspiration proportionality constant
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 1.5
    //                          ** min : 0
    //                          ** max : 100
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: tau
    //                          ** description : plant cover factor
    //                          ** variablecategory : exogenous
    //                          ** datatype : DOUBLE
    //                          ** default : 0.9983
    //                          ** min : 0
    //                          ** max : 100
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: tauAlpha
    //                          ** description : Fraction of the total net radiation exchanged at the soil surface when AlpaE = 1
    //                          ** parametercategory : soil
    //                          ** datatype : DOUBLE
    //                          ** default : 0.3
    //                          ** min : 0
    //                          ** max : 1
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
                //- outputs:
    //            * name: energyLimitedEvaporation
    //                          ** description : energy Limited Evaporation 
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 5000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
            double evapoTranspirationPriestlyTaylor = r.evapoTranspirationPriestlyTaylor;
            double tau = ex.tau;
            double energyLimitedEvaporation;
            double AlphaE;
            if (tau < tauAlpha)
            {
                AlphaE = 1.0d;
            }
            else
            {
                AlphaE = Alpha - ((Alpha - 1.0d) * (1.0d - tau) / (1.0d - tauAlpha));
            }
            energyLimitedEvaporation = evapoTranspirationPriestlyTaylor / Alpha * AlphaE * tau;
            a.energyLimitedEvaporation= energyLimitedEvaporation;
        }

    }
}