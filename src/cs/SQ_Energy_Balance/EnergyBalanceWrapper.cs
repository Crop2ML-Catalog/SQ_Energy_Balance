using System;
using System.Collections.Generic;
using System.Linq;
class EnergyBalanceWrapper
{
    private EnergyBalanceState s;
    private EnergyBalanceState s1;
    private EnergyBalanceRate r;
    private EnergyBalanceAuxiliary a;
    private EnergyBalanceExogenous ex;
    private EnergyBalanceComponent energybalanceComponent;

    public EnergyBalanceWrapper()
    {
        s = new EnergyBalanceState();
        r = new EnergyBalanceRate();
        a = new EnergyBalanceAuxiliary();
        ex = new EnergyBalanceExogenous();
        energybalanceComponent = new EnergyBalanceComponent();
        loadParameters();
    }

        double albedoCoefficient;
    double stefanBoltzman;
    double elevation;
    double lambdaV;
    double psychrometricConstant;
    double Alpha;
    double vonKarman;
    double heightWeatherMeasurements;
    double zm;
    double d;
    double zh;
    double soilDiffusionConstant;
    double rhoDensityAir;
    double specificHeatCapacityAir;
    double tau;
    double tauAlpha;
    int isWindVpDefined;

    public double diffusionLimitedEvaporation{ get { return s.diffusionLimitedEvaporation;}} 
     
    public double conductance{ get { return s.conductance;}} 
     
    public double minCanopyTemperature{ get { return s.minCanopyTemperature;}} 
     
    public double maxCanopyTemperature{ get { return s.maxCanopyTemperature;}} 
     
    public double evapoTranspirationPriestlyTaylor{ get { return r.evapoTranspirationPriestlyTaylor;}} 
     
    public double evapoTranspirationPenman{ get { return r.evapoTranspirationPenman;}} 
     
    public double evapoTranspiration{ get { return r.evapoTranspiration;}} 
     
    public double potentialTranspiration{ get { return r.potentialTranspiration;}} 
     
    public double soilHeatFlux{ get { return r.soilHeatFlux;}} 
     
    public double cropHeatFlux{ get { return r.cropHeatFlux;}} 
     
    public double netRadiation{ get { return a.netRadiation;}} 
     
    public double netOutGoingLongWaveRadiation{ get { return a.netOutGoingLongWaveRadiation;}} 
     
    public double netRadiationEquivalentEvaporation{ get { return a.netRadiationEquivalentEvaporation;}} 
     
    public double energyLimitedEvaporation{ get { return a.energyLimitedEvaporation;}} 
     
    public double soilEvaporation{ get { return a.soilEvaporation;}} 
     

    public EnergyBalanceWrapper(EnergyBalanceWrapper toCopy, bool copyAll) : this()
    {
        s = (toCopy.s != null) ? new EnergyBalanceState(toCopy.s, copyAll) : null;
        r = (toCopy.r != null) ? new EnergyBalanceRate(toCopy.r, copyAll) : null;
        a = (toCopy.a != null) ? new EnergyBalanceAuxiliary(toCopy.a, copyAll) : null;
        ex = (toCopy.ex != null) ? new EnergyBalanceExogenous(toCopy.ex, copyAll) : null;
        if (copyAll)
        {
            energybalanceComponent = (toCopy.energybalanceComponent != null) ? new EnergyBalanceComponent(toCopy.energybalanceComponent) : null;
        }
    }

    public void Init(){
        energybalanceComponent.Init(s, r, a);
        loadParameters();
    }

    private void loadParameters()
    {
        energybalanceComponent.albedoCoefficient = albedoCoefficient;
        energybalanceComponent.stefanBoltzman = stefanBoltzman;
        energybalanceComponent.elevation = elevation;
        energybalanceComponent.lambdaV = lambdaV;
        energybalanceComponent.psychrometricConstant = psychrometricConstant;
        energybalanceComponent.Alpha = Alpha;
        energybalanceComponent.vonKarman = vonKarman;
        energybalanceComponent.heightWeatherMeasurements = heightWeatherMeasurements;
        energybalanceComponent.zm = zm;
        energybalanceComponent.d = d;
        energybalanceComponent.zh = zh;
        energybalanceComponent.soilDiffusionConstant = soilDiffusionConstant;
        energybalanceComponent.rhoDensityAir = rhoDensityAir;
        energybalanceComponent.specificHeatCapacityAir = specificHeatCapacityAir;
        energybalanceComponent.tau = tau;
        energybalanceComponent.tauAlpha = tauAlpha;
        energybalanceComponent.isWindVpDefined = isWindVpDefined;
    }

    public void EstimateEnergyBalance(double minTair, double maxTair, double solarRadiation, double vaporPressure, double extraSolarRadiation, double hslope, double plantHeight, double wind, double deficitOnTopLayers, double VPDair)
    {
        a.minTair = minTair;
        a.maxTair = maxTair;
        a.solarRadiation = solarRadiation;
        a.vaporPressure = vaporPressure;
        a.extraSolarRadiation = extraSolarRadiation;
        a.hslope = hslope;
        a.plantHeight = plantHeight;
        a.wind = wind;
        a.deficitOnTopLayers = deficitOnTopLayers;
        a.VPDair = VPDair;
        energybalanceComponent.CalculateModel(s,s1, r, a, ex);
    }

}