
using System;
using System.Collections.Generic;
using CRA.ModelLayer.Core;
using System.Reflection;
using CRA.ModelLayer.ParametersManagement;   

namespace SiriusQualityEnergyBalance.DomainClass
{
    public class EnergyBalanceAuxiliary : ICloneable, IDomainClass
    {
        private double _minTair;
        private double _maxTair;
        private double _solarRadiation;
        private double _vaporPressure;
        private double _extraSolarRadiation;
        private double _hslope;
        private double _plantHeight;
        private double _wind;
        private double _deficitOnTopLayers;
        private double _VPDair;
        private double _netRadiation;
        private double _netOutGoingLongWaveRadiation;
        private double _netRadiationEquivalentEvaporation;
        private double _energyLimitedEvaporation;
        private double _soilEvaporation;
        private ParametersIO _parametersIO;

        public EnergyBalanceAuxiliary()
        {
            _parametersIO = new ParametersIO(this);
        }

        public EnergyBalanceAuxiliary(EnergyBalanceAuxiliary toCopy, bool copyAll) // copy constructor 
        {
            if (copyAll)
            {
                minTair = toCopy.minTair;
                maxTair = toCopy.maxTair;
                solarRadiation = toCopy.solarRadiation;
                vaporPressure = toCopy.vaporPressure;
                extraSolarRadiation = toCopy.extraSolarRadiation;
                hslope = toCopy.hslope;
                plantHeight = toCopy.plantHeight;
                wind = toCopy.wind;
                deficitOnTopLayers = toCopy.deficitOnTopLayers;
                VPDair = toCopy.VPDair;
                netRadiation = toCopy.netRadiation;
                netOutGoingLongWaveRadiation = toCopy.netOutGoingLongWaveRadiation;
                netRadiationEquivalentEvaporation = toCopy.netRadiationEquivalentEvaporation;
                energyLimitedEvaporation = toCopy.energyLimitedEvaporation;
                soilEvaporation = toCopy.soilEvaporation;
            }
        }

        public double minTair
        {
            get { return this._minTair; }
            set { this._minTair= value; } 
        }
        public double maxTair
        {
            get { return this._maxTair; }
            set { this._maxTair= value; } 
        }
        public double solarRadiation
        {
            get { return this._solarRadiation; }
            set { this._solarRadiation= value; } 
        }
        public double vaporPressure
        {
            get { return this._vaporPressure; }
            set { this._vaporPressure= value; } 
        }
        public double extraSolarRadiation
        {
            get { return this._extraSolarRadiation; }
            set { this._extraSolarRadiation= value; } 
        }
        public double hslope
        {
            get { return this._hslope; }
            set { this._hslope= value; } 
        }
        public double plantHeight
        {
            get { return this._plantHeight; }
            set { this._plantHeight= value; } 
        }
        public double wind
        {
            get { return this._wind; }
            set { this._wind= value; } 
        }
        public double deficitOnTopLayers
        {
            get { return this._deficitOnTopLayers; }
            set { this._deficitOnTopLayers= value; } 
        }
        public double VPDair
        {
            get { return this._VPDair; }
            set { this._VPDair= value; } 
        }
        public double netRadiation
        {
            get { return this._netRadiation; }
            set { this._netRadiation= value; } 
        }
        public double netOutGoingLongWaveRadiation
        {
            get { return this._netOutGoingLongWaveRadiation; }
            set { this._netOutGoingLongWaveRadiation= value; } 
        }
        public double netRadiationEquivalentEvaporation
        {
            get { return this._netRadiationEquivalentEvaporation; }
            set { this._netRadiationEquivalentEvaporation= value; } 
        }
        public double energyLimitedEvaporation
        {
            get { return this._energyLimitedEvaporation; }
            set { this._energyLimitedEvaporation= value; } 
        }
        public double soilEvaporation
        {
            get { return this._soilEvaporation; }
            set { this._soilEvaporation= value; } 
        }

        public string Description
        {
            get { return "EnergyBalanceAuxiliary of the component";}
        }

        public string URL
        {
            get { return "http://" ;}
        }

        public virtual IDictionary<string, PropertyInfo> PropertiesDescription
        {
            get { return _parametersIO.GetCachedProperties(typeof(IDomainClass));}
        }

        public virtual Boolean ClearValues()
        {
             _minTair = default(double);
             _maxTair = default(double);
             _solarRadiation = default(double);
             _vaporPressure = default(double);
             _extraSolarRadiation = default(double);
             _hslope = default(double);
             _plantHeight = default(double);
             _wind = default(double);
             _deficitOnTopLayers = default(double);
             _VPDair = default(double);
             _netRadiation = default(double);
             _netOutGoingLongWaveRadiation = default(double);
             _netRadiationEquivalentEvaporation = default(double);
             _energyLimitedEvaporation = default(double);
             _soilEvaporation = default(double);
            return true;
        }

        public virtual Object Clone()
        {
            IDomainClass myclass = (IDomainClass) this.MemberwiseClone();
            _parametersIO.PopulateClonedCopy(myclass);
            return myclass;
        }
    }
}