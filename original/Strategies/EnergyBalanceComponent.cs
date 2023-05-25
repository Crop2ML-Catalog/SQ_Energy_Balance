
using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml;     

using SiriusQualityEnergyBalance.DomainClass;
namespace SiriusQualityEnergyBalance.Strategies
{
    public class EnergyBalanceComponent 
    {
        public EnergyBalanceComponent()
        {
        }

        public double albedoCoefficient
        {
            get
            {
                 return _NetRadiation.albedoCoefficient; 
            }
            set
            {
                _NetRadiation.albedoCoefficient = value;
            }
        }
        public double stefanBoltzman
        {
            get
            {
                 return _NetRadiation.stefanBoltzman; 
            }
            set
            {
                _NetRadiation.stefanBoltzman = value;
            }
        }
        public double elevation
        {
            get
            {
                 return _NetRadiation.elevation; 
            }
            set
            {
                _NetRadiation.elevation = value;
            }
        }
        public double lambdaV
        {
            get
            {
                 return _NetRadiationEquivalentEvaporation.lambdaV; 
            }
            set
            {
                _NetRadiationEquivalentEvaporation.lambdaV = value;
                _Penman.lambdaV = value;
                _CanopyTemperature.lambdaV = value;
            }
        }
        public double psychrometricConstant
        {
            get
            {
                 return _PriestlyTaylor.psychrometricConstant; 
            }
            set
            {
                _PriestlyTaylor.psychrometricConstant = value;
                _Penman.psychrometricConstant = value;
            }
        }
        public double Alpha
        {
            get
            {
                 return _PriestlyTaylor.Alpha; 
            }
            set
            {
                _PriestlyTaylor.Alpha = value;
                _Penman.Alpha = value;
                _PtSoil.Alpha = value;
            }
        }
        public double vonKarman
        {
            get
            {
                 return _Conductance.vonKarman; 
            }
            set
            {
                _Conductance.vonKarman = value;
            }
        }
        public double heightWeatherMeasurements
        {
            get
            {
                 return _Conductance.heightWeatherMeasurements; 
            }
            set
            {
                _Conductance.heightWeatherMeasurements = value;
            }
        }
        public double zm
        {
            get
            {
                 return _Conductance.zm; 
            }
            set
            {
                _Conductance.zm = value;
            }
        }
        public double d
        {
            get
            {
                 return _Conductance.d; 
            }
            set
            {
                _Conductance.d = value;
            }
        }
        public double zh
        {
            get
            {
                 return _Conductance.zh; 
            }
            set
            {
                _Conductance.zh = value;
            }
        }
        public double soilDiffusionConstant
        {
            get
            {
                 return _DiffusionLimitedEvaporation.soilDiffusionConstant; 
            }
            set
            {
                _DiffusionLimitedEvaporation.soilDiffusionConstant = value;
            }
        }
        public double rhoDensityAir
        {
            get
            {
                 return _Penman.rhoDensityAir; 
            }
            set
            {
                _Penman.rhoDensityAir = value;
                _CanopyTemperature.rhoDensityAir = value;
            }
        }
        public double specificHeatCapacityAir
        {
            get
            {
                 return _Penman.specificHeatCapacityAir; 
            }
            set
            {
                _Penman.specificHeatCapacityAir = value;
                _CanopyTemperature.specificHeatCapacityAir = value;
            }
        }
        public double tauAlpha
        {
            get
            {
                 return _PtSoil.tauAlpha; 
            }
            set
            {
                _PtSoil.tauAlpha = value;
            }
        }
        public int isWindVpDefined
        {
            get
            {
                 return _EvapoTranspiration.isWindVpDefined; 
            }
            set
            {
                _EvapoTranspiration.isWindVpDefined = value;
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

        private void CalculateModel(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            EstimateOfAssociatedClasses(s, s1, r, a, ex);
        }

        //Declaration of the associated strategies
        NetRadiation _NetRadiation = new NetRadiation();
        NetRadiationEquivalentEvaporation _NetRadiationEquivalentEvaporation = new NetRadiationEquivalentEvaporation();
        PriestlyTaylor _PriestlyTaylor = new PriestlyTaylor();
        Conductance _Conductance = new Conductance();
        DiffusionLimitedEvaporation _DiffusionLimitedEvaporation = new DiffusionLimitedEvaporation();
        Penman _Penman = new Penman();
        PtSoil _PtSoil = new PtSoil();
        SoilEvaporation _SoilEvaporation = new SoilEvaporation();
        EvapoTranspiration _EvapoTranspiration = new EvapoTranspiration();
        SoilHeatFlux _SoilHeatFlux = new SoilHeatFlux();
        PotentialTranspiration _PotentialTranspiration = new PotentialTranspiration();
        CropHeatFlux _CropHeatFlux = new CropHeatFlux();
        CanopyTemperature _CanopyTemperature = new CanopyTemperature();

        private void EstimateOfAssociatedClasses(SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceState s1,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceRate r,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceAuxiliary a,SiriusQualityEnergyBalance.DomainClass.EnergyBalanceExogenous ex)
        {
            _NetRadiation.Estimate(s,s1, r, a, ex);
            _Conductance.Estimate(s,s1, r, a, ex);
            _DiffusionLimitedEvaporation.Estimate(s,s1, r, a, ex);
            _NetRadiationEquivalentEvaporation.Estimate(s,s1, r, a, ex);
            _PriestlyTaylor.Estimate(s,s1, r, a, ex);
            _PtSoil.Estimate(s,s1, r, a, ex);
            _Penman.Estimate(s,s1, r, a, ex);
            _SoilEvaporation.Estimate(s,s1, r, a, ex);
            _EvapoTranspiration.Estimate(s,s1, r, a, ex);
            _SoilHeatFlux.Estimate(s,s1, r, a, ex);
            _PotentialTranspiration.Estimate(s,s1, r, a, ex);
            _CropHeatFlux.Estimate(s,s1, r, a, ex);
            _CanopyTemperature.Estimate(s,s1, r, a, ex);
        }

        public EnergyBalanceComponent(EnergyBalanceComponent toCopy): this() // copy constructor 
        {
                albedoCoefficient = toCopy.albedoCoefficient;
                stefanBoltzman = toCopy.stefanBoltzman;
                elevation = toCopy.elevation;
                lambdaV = toCopy.lambdaV;
                psychrometricConstant = toCopy.psychrometricConstant;
                Alpha = toCopy.Alpha;
                vonKarman = toCopy.vonKarman;
                heightWeatherMeasurements = toCopy.heightWeatherMeasurements;
                zm = toCopy.zm;
                d = toCopy.d;
                zh = toCopy.zh;
                soilDiffusionConstant = toCopy.soilDiffusionConstant;
                rhoDensityAir = toCopy.rhoDensityAir;
                specificHeatCapacityAir = toCopy.specificHeatCapacityAir;
                tauAlpha = toCopy.tauAlpha;
                isWindVpDefined = toCopy.isWindVpDefined;
        }
    }
}