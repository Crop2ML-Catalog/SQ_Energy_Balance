
using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;
using CRA.ModelLayer.MetadataTypes;
using CRA.ModelLayer.Core;
using CRA.ModelLayer.Strategy;
using System.Reflection;
using VarInfo=CRA.ModelLayer.Core.VarInfo;
using Preconditions=CRA.ModelLayer.Core.Preconditions;
using CRA.AgroManagement;       
using SiriusQualityEnergyBalance.DomainClass;
namespace SiriusQualityEnergyBalance.Strategies
{
    public class PtSoil : IStrategySiriusQualityEnergyBalance
    {
        public PtSoil()
        {
            ModellingOptions mo0_0 = new ModellingOptions();
            //Parameters
            List<VarInfo> _parameters0_0 = new List<VarInfo>();
            VarInfo v1 = new VarInfo();
            v1.DefaultValue = 1.5;
            v1.Description = "Priestley-Taylor evapotranspiration proportionality constant";
            v1.Id = 0;
            v1.MaxValue = 100;
            v1.MinValue = 0;
            v1.Name = "Alpha";
            v1.Size = 1;
            v1.Units = "";
            v1.URL = "";
            v1.VarType = CRA.ModelLayer.Core.VarInfo.Type.PARAMETER;
            v1.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
            _parameters0_0.Add(v1);
            VarInfo v2 = new VarInfo();
            v2.DefaultValue = 0.9983;
            v2.Description = "plant cover factor";
            v2.Id = 0;
            v2.MaxValue = 100;
            v2.MinValue = 0;
            v2.Name = "tau";
            v2.Size = 1;
            v2.Units = "";
            v2.URL = "";
            v2.VarType = CRA.ModelLayer.Core.VarInfo.Type.PARAMETER;
            v2.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
            _parameters0_0.Add(v2);
            VarInfo v3 = new VarInfo();
            v3.DefaultValue = 0.3;
            v3.Description = "Fraction of the total net radiation exchanged at the soil surface when AlpaE = 1";
            v3.Id = 0;
            v3.MaxValue = 1;
            v3.MinValue = 0;
            v3.Name = "tauAlpha";
            v3.Size = 1;
            v3.Units = "";
            v3.URL = "";
            v3.VarType = CRA.ModelLayer.Core.VarInfo.Type.PARAMETER;
            v3.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
            _parameters0_0.Add(v3);
            mo0_0.Parameters=_parameters0_0;

            //Inputs
            List<PropertyDescription> _inputs0_0 = new List<PropertyDescription>();
            PropertyDescription pd1 = new PropertyDescription();
            pd1.DomainClassType = typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate);
            pd1.PropertyName = "evapoTranspirationPriestlyTaylor";
            pd1.PropertyType = (SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRateVarInfo.evapoTranspirationPriestlyTaylor).ValueType.TypeForCurrentValue;
            pd1.PropertyVarInfo =(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRateVarInfo.evapoTranspirationPriestlyTaylor);
            _inputs0_0.Add(pd1);
            mo0_0.Inputs=_inputs0_0;

            //Outputs
            List<PropertyDescription> _outputs0_0 = new List<PropertyDescription>();
            PropertyDescription pd2 = new PropertyDescription();
            pd2.DomainClassType = typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary);
            pd2.PropertyName = "energyLimitedEvaporation";
            pd2.PropertyType = (SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.energyLimitedEvaporation).ValueType.TypeForCurrentValue;
            pd2.PropertyVarInfo =(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.energyLimitedEvaporation);
            _outputs0_0.Add(pd2);
            mo0_0.Outputs=_outputs0_0;
            //Associated strategies
            List<string> lAssStrat0_0 = new List<string>();
            mo0_0.AssociatedStrategies = lAssStrat0_0;
            //Adding the modeling options to the modeling options manager
            _modellingOptionsManager = new ModellingOptionsManager(mo0_0);
            SetStaticParametersVarInfoDefinitions();
            SetPublisherData();

        }

        public string Description
        {
            get { return "Evaporation from the soil in the energy-limited stage " ;}
        }

        public string URL
        {
            get { return "" ;}
        }

        public string Domain
        {
            get { return "";}
        }

        public string ModelType
        {
            get { return "";}
        }

        public bool IsContext
        {
            get { return false;}
        }

        public IList<int> TimeStep
        {
            get
            {
                IList<int> ts = new List<int>();
                return ts;
            }
        }

        private  PublisherData _pd;
        public PublisherData PublisherData
        {
            get { return _pd;} 
        }

        private  void SetPublisherData()
        {
            _pd = new CRA.ModelLayer.MetadataTypes.PublisherData();
            _pd.Add("Creator", "Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin");
            _pd.Add("Date", "");
            _pd.Add("Publisher", "New Zealand Institute for Crop and Food Research Ltd., New Zealand Institute for Crop and Food Research Ltd., New Zealand Institute for Crop and Food Research Ltd., New Zealand Institute for Crop and Food Research Ltd. "); 
        }

        private ModellingOptionsManager _modellingOptionsManager;
        public ModellingOptionsManager ModellingOptionsManager
        {
            get { return _modellingOptionsManager; } 
        }

        public IEnumerable<Type> GetStrategyDomainClassesTypes()
        {
            return new List<Type>() {  typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState),  typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState), typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate), typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary), typeof(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous)};
        }

        // Getter and setters for the value of the parameters of the strategy. The actual parameters are stored into the ModelingOptionsManager of the strategy.

        public double Alpha
        {
            get { 
                VarInfo vi= _modellingOptionsManager.GetParameterByName("Alpha");
                if (vi != null && vi.CurrentValue!=null) return (double)vi.CurrentValue ;
                else throw new Exception("Parameter 'Alpha' not found (or found null) in strategy 'PtSoil'");
            } set {
                VarInfo vi = _modellingOptionsManager.GetParameterByName("Alpha");
                if (vi != null)  vi.CurrentValue=value;
                else throw new Exception("Parameter 'Alpha' not found in strategy 'PtSoil'");
            }
        }
        public double tau
        {
            get { 
                VarInfo vi= _modellingOptionsManager.GetParameterByName("tau");
                if (vi != null && vi.CurrentValue!=null) return (double)vi.CurrentValue ;
                else throw new Exception("Parameter 'tau' not found (or found null) in strategy 'PtSoil'");
            } set {
                VarInfo vi = _modellingOptionsManager.GetParameterByName("tau");
                if (vi != null)  vi.CurrentValue=value;
                else throw new Exception("Parameter 'tau' not found in strategy 'PtSoil'");
            }
        }
        public double tauAlpha
        {
            get { 
                VarInfo vi= _modellingOptionsManager.GetParameterByName("tauAlpha");
                if (vi != null && vi.CurrentValue!=null) return (double)vi.CurrentValue ;
                else throw new Exception("Parameter 'tauAlpha' not found (or found null) in strategy 'PtSoil'");
            } set {
                VarInfo vi = _modellingOptionsManager.GetParameterByName("tauAlpha");
                if (vi != null)  vi.CurrentValue=value;
                else throw new Exception("Parameter 'tauAlpha' not found in strategy 'PtSoil'");
            }
        }

        public void SetParametersDefaultValue()
        {
            _modellingOptionsManager.SetParametersDefaultValue();
        }

        private static void SetStaticParametersVarInfoDefinitions()
        {

            AlphaVarInfo.Name = "Alpha";
            AlphaVarInfo.Description = "Priestley-Taylor evapotranspiration proportionality constant";
            AlphaVarInfo.MaxValue = 100;
            AlphaVarInfo.MinValue = 0;
            AlphaVarInfo.DefaultValue = 1.5;
            AlphaVarInfo.Units = "";
            AlphaVarInfo.ValueType = VarInfoValueTypes.GetInstanceForName("Double");

            tauVarInfo.Name = "tau";
            tauVarInfo.Description = "plant cover factor";
            tauVarInfo.MaxValue = 100;
            tauVarInfo.MinValue = 0;
            tauVarInfo.DefaultValue = 0.9983;
            tauVarInfo.Units = "";
            tauVarInfo.ValueType = VarInfoValueTypes.GetInstanceForName("Double");

            tauAlphaVarInfo.Name = "tauAlpha";
            tauAlphaVarInfo.Description = "Fraction of the total net radiation exchanged at the soil surface when AlpaE = 1";
            tauAlphaVarInfo.MaxValue = 1;
            tauAlphaVarInfo.MinValue = 0;
            tauAlphaVarInfo.DefaultValue = 0.3;
            tauAlphaVarInfo.Units = "";
            tauAlphaVarInfo.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
        }

        private static VarInfo _AlphaVarInfo = new VarInfo();
        public static VarInfo AlphaVarInfo
        {
            get { return _AlphaVarInfo;} 
        }

        private static VarInfo _tauVarInfo = new VarInfo();
        public static VarInfo tauVarInfo
        {
            get { return _tauVarInfo;} 
        }

        private static VarInfo _tauAlphaVarInfo = new VarInfo();
        public static VarInfo tauAlphaVarInfo
        {
            get { return _tauAlphaVarInfo;} 
        }

        public string TestPostConditions(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex,string callID)
        {
            try
            {
                //Set current values of the outputs to the static VarInfo representing the output properties of the domain classes
                SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.energyLimitedEvaporation.CurrentValue=a.energyLimitedEvaporation;
                ConditionsCollection prc = new ConditionsCollection();
                Preconditions pre = new Preconditions(); 
                RangeBasedCondition r5 = new RangeBasedCondition(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.energyLimitedEvaporation);
                if(r5.ApplicableVarInfoValueTypes.Contains( SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.energyLimitedEvaporation.ValueType)){prc.AddCondition(r5);}
                string postConditionsResult = pre.VerifyPostconditions(prc, callID); if (!string.IsNullOrEmpty(postConditionsResult)) { pre.TestsOut(postConditionsResult, true, "PostConditions errors in strategy " + this.GetType().Name); } return postConditionsResult;
            }
            catch (Exception exception)
            {
                string msg = "SiriusQuality.EnergyBalance, " + this.GetType().Name + ": Unhandled exception running post-condition test. ";
                throw new Exception(msg, exception);
            }
        }

        public string TestPreConditions(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex,string callID)
        {
            try
            {
                //Set current values of the inputs to the static VarInfo representing the inputs properties of the domain classes
                SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRateVarInfo.evapoTranspirationPriestlyTaylor.CurrentValue=r.evapoTranspirationPriestlyTaylor;
                ConditionsCollection prc = new ConditionsCollection();
                Preconditions pre = new Preconditions(); 
                RangeBasedCondition r1 = new RangeBasedCondition(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRateVarInfo.evapoTranspirationPriestlyTaylor);
                if(r1.ApplicableVarInfoValueTypes.Contains( SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRateVarInfo.evapoTranspirationPriestlyTaylor.ValueType)){prc.AddCondition(r1);}
                prc.AddCondition(new RangeBasedCondition(_modellingOptionsManager.GetParameterByName("Alpha")));
                prc.AddCondition(new RangeBasedCondition(_modellingOptionsManager.GetParameterByName("tau")));
                prc.AddCondition(new RangeBasedCondition(_modellingOptionsManager.GetParameterByName("tauAlpha")));
                string preConditionsResult = pre.VerifyPreconditions(prc, callID); if (!string.IsNullOrEmpty(preConditionsResult)) { pre.TestsOut(preConditionsResult, true, "PreConditions errors in strategy " + this.GetType().Name); } return preConditionsResult;
            }
            catch (Exception exception)
            {
                string msg = "SiriusQuality.EnergyBalance, " + this.GetType().Name + ": Unhandled exception running pre-condition test. ";
                throw new Exception(msg, exception);
            }
        }

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

        private void CalculateModel(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a, SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            double evapoTranspirationPriestlyTaylor = r.evapoTranspirationPriestlyTaylor;
            double energyLimitedEvaporation;
            double AlphaE;
            if (tau < tauAlpha)
            {
                AlphaE = 1.00d;
            }
            else
            {
                AlphaE = Alpha - ((Alpha - 1.00d) * (1.00d - tau) / (1.00d - tauAlpha));
            }
            energyLimitedEvaporation = evapoTranspirationPriestlyTaylor / Alpha * AlphaE * tau;
            a.energyLimitedEvaporation= energyLimitedEvaporation;
        }

    }
}