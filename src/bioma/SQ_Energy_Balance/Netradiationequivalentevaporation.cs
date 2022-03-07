
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
using EnergyBalance.DomainClass;
namespace EnergyBalance.Strategies
{
    public class NetRadiationEquivalentEvaporation : IStrategyEnergyBalance
    {
        public NetRadiationEquivalentEvaporation()
        {
            ModellingOptions mo0_0 = new ModellingOptions();
            //Parameters
            List<VarInfo> _parameters0_0 = new List<VarInfo>();
            VarInfo v1 = new VarInfo();
            v1.DefaultValue = 2.454;
            v1.Description = "latent heat of vaporization of water";
            v1.Id = 0;
            v1.MaxValue = 10;
            v1.MinValue = 0;
            v1.Name = "lambdaV";
            v1.Size = 1;
            v1.Units = "MJ kg-1";
            v1.URL = "";
            v1.VarType = CRA.ModelLayer.Core.VarInfo.Type.PARAMETER;
            v1.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
            _parameters0_0.Add(v1);
            mo0_0.Parameters=_parameters0_0;

            //Inputs
            List<PropertyDescription> _inputs0_0 = new List<PropertyDescription>();
            PropertyDescription pd1 = new PropertyDescription();
            pd1.DomainClassType = typeof(EnergyBalance.DomainClass.EnergyBalanceState);
            pd1.PropertyName = "netRadiation";
            pd1.PropertyType = (EnergyBalance.DomainClass.EnergyBalanceStateVarInfo.netRadiation).ValueType.TypeForCurrentValue;
            pd1.PropertyVarInfo =(EnergyBalance.DomainClass.EnergyBalanceStateVarInfo.netRadiation);
            _inputs0_0.Add(pd1);
            mo0_0.Inputs=_inputs0_0;

            //Outputs
            List<PropertyDescription> _outputs0_0 = new List<PropertyDescription>();
            PropertyDescription pd2 = new PropertyDescription();
            pd2.DomainClassType = typeof(EnergyBalance.DomainClass.EnergyBalanceAuxiliary);
            pd2.PropertyName = "netRadiationEquivalentEvaporation";
            pd2.PropertyType = (EnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.netRadiationEquivalentEvaporation).ValueType.TypeForCurrentValue;
            pd2.PropertyVarInfo =(EnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.netRadiationEquivalentEvaporation);
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
            get { return " It is given by dividing net radiation by latent heat of vaporization of water " ;}
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
            _pd.Add("Publisher", "New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        ");
        }

        private ModellingOptionsManager _modellingOptionsManager;
        public ModellingOptionsManager ModellingOptionsManager
        {
            get { return _modellingOptionsManager; } 
        }

        public IEnumerable<Type> GetStrategyDomainClassesTypes()
        {
            return new List<Type>() {  typeof(EnergyBalance.DomainClass.EnergyBalanceState),  typeof(EnergyBalance.DomainClass.EnergyBalanceState), typeof(EnergyBalance.DomainClass.EnergyBalanceRate), typeof(EnergyBalance.DomainClass.EnergyBalanceAuxiliary), typeof(EnergyBalance.DomainClass.EnergyBalanceExogenous)};
        }

        // Getter and setters for the value of the parameters of the strategy. The actual parameters are stored into the ModelingOptionsManager of the strategy.

        public double lambdaV
        {
            get { 
                VarInfo vi= _modellingOptionsManager.GetParameterByName("lambdaV");
                if (vi != null && vi.CurrentValue!=null) return (double)vi.CurrentValue ;
                else throw new Exception("Parameter 'lambdaV' not found (or found null) in strategy 'NetRadiationEquivalentEvaporation'");
            } set {
                VarInfo vi = _modellingOptionsManager.GetParameterByName("lambdaV");
                if (vi != null)  vi.CurrentValue=value;
                else throw new Exception("Parameter 'lambdaV' not found in strategy 'NetRadiationEquivalentEvaporation'");
            }
        }

        public void SetParametersDefaultValue()
        {
            _modellingOptionsManager.SetParametersDefaultValue();
        }

        private static void SetStaticParametersVarInfoDefinitions()
        {

            lambdaVVarInfo.Name = "lambdaV";
            lambdaVVarInfo.Description = "latent heat of vaporization of water";
            lambdaVVarInfo.MaxValue = 10;
            lambdaVVarInfo.MinValue = 0;
            lambdaVVarInfo.DefaultValue = 2.454;
            lambdaVVarInfo.Units = "MJ kg-1";
            lambdaVVarInfo.ValueType = VarInfoValueTypes.GetInstanceForName("Double");
        }

        private static VarInfo _lambdaVVarInfo = new VarInfo();
        public static VarInfo lambdaVVarInfo
        {
            get { return _lambdaVVarInfo;} 
        }

        public string TestPostConditions(EnergyBalance.DomainClass.EnergyBalanceState s,EnergyBalance.DomainClass.EnergyBalanceState s1,EnergyBalance.DomainClass.EnergyBalanceRate r,EnergyBalance.DomainClass.EnergyBalanceAuxiliary a,EnergyBalance.DomainClass.EnergyBalanceExogenous ex,string callID)
        {
            try
            {
                //Set current values of the outputs to the static VarInfo representing the output properties of the domain classes
                EnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.netRadiationEquivalentEvaporation.CurrentValue=a.netRadiationEquivalentEvaporation;
                ConditionsCollection prc = new ConditionsCollection();
                Preconditions pre = new Preconditions(); 
                RangeBasedCondition r3 = new RangeBasedCondition(EnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.netRadiationEquivalentEvaporation);
                if(r3.ApplicableVarInfoValueTypes.Contains( EnergyBalance.DomainClass.EnergyBalanceAuxiliaryVarInfo.netRadiationEquivalentEvaporation.ValueType)){prc.AddCondition(r3);}
                string postConditionsResult = pre.VerifyPostconditions(prc, callID); if (!string.IsNullOrEmpty(postConditionsResult)) { pre.TestsOut(postConditionsResult, true, "PostConditions errors in strategy " + this.GetType().Name); } return postConditionsResult;
            }
            catch (Exception exception)
            {
                string msg = ".EnergyBalance, " + this.GetType().Name + ": Unhandled exception running post-condition test. ";
                throw new Exception(msg, exception);
            }
        }

        public string TestPreConditions(EnergyBalance.DomainClass.EnergyBalanceState s,EnergyBalance.DomainClass.EnergyBalanceState s1,EnergyBalance.DomainClass.EnergyBalanceRate r,EnergyBalance.DomainClass.EnergyBalanceAuxiliary a,EnergyBalance.DomainClass.EnergyBalanceExogenous ex,string callID)
        {
            try
            {
                //Set current values of the inputs to the static VarInfo representing the inputs properties of the domain classes
                EnergyBalance.DomainClass.EnergyBalanceStateVarInfo.netRadiation.CurrentValue=s.netRadiation;
                ConditionsCollection prc = new ConditionsCollection();
                Preconditions pre = new Preconditions(); 
                RangeBasedCondition r1 = new RangeBasedCondition(EnergyBalance.DomainClass.EnergyBalanceStateVarInfo.netRadiation);
                if(r1.ApplicableVarInfoValueTypes.Contains( EnergyBalance.DomainClass.EnergyBalanceStateVarInfo.netRadiation.ValueType)){prc.AddCondition(r1);}
                prc.AddCondition(new RangeBasedCondition(_modellingOptionsManager.GetParameterByName("lambdaV")));
                string preConditionsResult = pre.VerifyPreconditions(prc, callID); if (!string.IsNullOrEmpty(preConditionsResult)) { pre.TestsOut(preConditionsResult, true, "PreConditions errors in strategy " + this.GetType().Name); } return preConditionsResult;
            }
            catch (Exception exception)
            {
                string msg = ".EnergyBalance, " + this.GetType().Name + ": Unhandled exception running pre-condition test. ";
                throw new Exception(msg, exception);
            }
        }

        public void Estimate(EnergyBalance.DomainClass.EnergyBalanceState s,EnergyBalance.DomainClass.EnergyBalanceState s1,EnergyBalance.DomainClass.EnergyBalanceRate r,EnergyBalance.DomainClass.EnergyBalanceAuxiliary a,EnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            try
            {
                CalculateModel(s, s1, r, a, ex);
            }
            catch (Exception exception)
            {
                string msg = "Error in component EnergyBalance, strategy: " + this.GetType().Name + ": Unhandled exception running model. "+exception.GetType().FullName+" - "+exception.Message;
                throw new Exception(msg, exception);
            }
        }

        private void CalculateModel(EnergyBalance.DomainClass.EnergyBalanceState s, EnergyBalance.DomainClass.EnergyBalanceState s1, EnergyBalance.DomainClass.EnergyBalanceRate r, EnergyBalance.DomainClass.EnergyBalanceAuxiliary a, EnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            double netRadiation = s.netRadiation;
            double netRadiationEquivalentEvaporation;
            netRadiationEquivalentEvaporation = netRadiation / lambdaV * 1000.0d;
            a.netRadiationEquivalentEvaporation= netRadiationEquivalentEvaporation;
        }

    }
}