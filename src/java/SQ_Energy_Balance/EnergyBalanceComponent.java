public class EnergyBalanceComponent
{
    
    public EnergyBalanceComponent() { }

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

    public Double getalbedoCoefficient()
    { return _NetRadiation.getalbedoCoefficient(); }
    public void setalbedoCoefficient(Double _albedoCoefficient){
    _NetRadiation.setalbedoCoefficient(_albedoCoefficient);
    }

    public Double getstefanBoltzman()
    { return _NetRadiation.getstefanBoltzman(); }
    public void setstefanBoltzman(Double _stefanBoltzman){
    _NetRadiation.setstefanBoltzman(_stefanBoltzman);
    }

    public Double getelevation()
    { return _NetRadiation.getelevation(); }
    public void setelevation(Double _elevation){
    _NetRadiation.setelevation(_elevation);
    }

    public Double getlambdaV()
    { return _NetRadiationEquivalentEvaporation.getlambdaV(); }
    public void setlambdaV(Double _lambdaV){
    _NetRadiationEquivalentEvaporation.setlambdaV(_lambdaV);
    _Penman.setlambdaV(_lambdaV);
    _CanopyTemperature.setlambdaV(_lambdaV);
    }

    public Double getpsychrometricConstant()
    { return _PriestlyTaylor.getpsychrometricConstant(); }
    public void setpsychrometricConstant(Double _psychrometricConstant){
    _PriestlyTaylor.setpsychrometricConstant(_psychrometricConstant);
    _Penman.setpsychrometricConstant(_psychrometricConstant);
    }

    public Double getAlpha()
    { return _PriestlyTaylor.getAlpha(); }
    public void setAlpha(Double _Alpha){
    _PriestlyTaylor.setAlpha(_Alpha);
    _Penman.setAlpha(_Alpha);
    _PtSoil.setAlpha(_Alpha);
    }

    public Double getvonKarman()
    { return _Conductance.getvonKarman(); }
    public void setvonKarman(Double _vonKarman){
    _Conductance.setvonKarman(_vonKarman);
    }

    public Double getheightWeatherMeasurements()
    { return _Conductance.getheightWeatherMeasurements(); }
    public void setheightWeatherMeasurements(Double _heightWeatherMeasurements){
    _Conductance.setheightWeatherMeasurements(_heightWeatherMeasurements);
    }

    public Double getzm()
    { return _Conductance.getzm(); }
    public void setzm(Double _zm){
    _Conductance.setzm(_zm);
    }

    public Double getd()
    { return _Conductance.getd(); }
    public void setd(Double _d){
    _Conductance.setd(_d);
    }

    public Double getzh()
    { return _Conductance.getzh(); }
    public void setzh(Double _zh){
    _Conductance.setzh(_zh);
    }

    public Double getsoilDiffusionConstant()
    { return _DiffusionLimitedEvaporation.getsoilDiffusionConstant(); }
    public void setsoilDiffusionConstant(Double _soilDiffusionConstant){
    _DiffusionLimitedEvaporation.setsoilDiffusionConstant(_soilDiffusionConstant);
    }

    public Double getrhoDensityAir()
    { return _Penman.getrhoDensityAir(); }
    public void setrhoDensityAir(Double _rhoDensityAir){
    _Penman.setrhoDensityAir(_rhoDensityAir);
    _CanopyTemperature.setrhoDensityAir(_rhoDensityAir);
    }

    public Double getspecificHeatCapacityAir()
    { return _Penman.getspecificHeatCapacityAir(); }
    public void setspecificHeatCapacityAir(Double _specificHeatCapacityAir){
    _Penman.setspecificHeatCapacityAir(_specificHeatCapacityAir);
    _CanopyTemperature.setspecificHeatCapacityAir(_specificHeatCapacityAir);
    }

    public Double gettau()
    { return _PtSoil.gettau(); }
    public void settau(Double _tau){
    _PtSoil.settau(_tau);
    _SoilHeatFlux.settau(_tau);
    _PotentialTranspiration.settau(_tau);
    }

    public Double gettauAlpha()
    { return _PtSoil.gettauAlpha(); }
    public void settauAlpha(Double _tauAlpha){
    _PtSoil.settauAlpha(_tauAlpha);
    }

    public Integer getisWindVpDefined()
    { return _EvapoTranspiration.getisWindVpDefined(); }
    public void setisWindVpDefined(Integer _isWindVpDefined){
    _EvapoTranspiration.setisWindVpDefined(_isWindVpDefined);
    }
    public void  Calculate_Model(EnergyBalanceState s, EnergyBalanceState s1, EnergyBalanceRate r, EnergyBalanceAuxiliary a, EnergyBalanceExogenous ex)
    {
        _NetRadiation.Calculate_Model(s, s1, r, a, ex);
        _Conductance.Calculate_Model(s, s1, r, a, ex);
        _DiffusionLimitedEvaporation.Calculate_Model(s, s1, r, a, ex);
        _NetRadiationEquivalentEvaporation.Calculate_Model(s, s1, r, a, ex);
        _PriestlyTaylor.Calculate_Model(s, s1, r, a, ex);
        _PtSoil.Calculate_Model(s, s1, r, a, ex);
        _Penman.Calculate_Model(s, s1, r, a, ex);
        _SoilEvaporation.Calculate_Model(s, s1, r, a, ex);
        _EvapoTranspiration.Calculate_Model(s, s1, r, a, ex);
        _SoilHeatFlux.Calculate_Model(s, s1, r, a, ex);
        _PotentialTranspiration.Calculate_Model(s, s1, r, a, ex);
        _CropHeatFlux.Calculate_Model(s, s1, r, a, ex);
        _CanopyTemperature.Calculate_Model(s, s1, r, a, ex);
    }
    private Double albedoCoefficient;
    private Double stefanBoltzman;
    private Double elevation;
    private Double lambdaV;
    private Double psychrometricConstant;
    private Double Alpha;
    private Double vonKarman;
    private Double heightWeatherMeasurements;
    private Double zm;
    private Double d;
    private Double zh;
    private Double soilDiffusionConstant;
    private Double rhoDensityAir;
    private Double specificHeatCapacityAir;
    private Double tau;
    private Double tauAlpha;
    private Integer isWindVpDefined;
    public EnergyBalanceComponent(EnergyBalanceComponent toCopy) // copy constructor 
    {
        this.albedoCoefficient = toCopy.getalbedoCoefficient();
        this.stefanBoltzman = toCopy.getstefanBoltzman();
        this.elevation = toCopy.getelevation();
        this.lambdaV = toCopy.getlambdaV();
        this.psychrometricConstant = toCopy.getpsychrometricConstant();
        this.Alpha = toCopy.getAlpha();
        this.vonKarman = toCopy.getvonKarman();
        this.heightWeatherMeasurements = toCopy.getheightWeatherMeasurements();
        this.zm = toCopy.getzm();
        this.d = toCopy.getd();
        this.zh = toCopy.getzh();
        this.soilDiffusionConstant = toCopy.getsoilDiffusionConstant();
        this.rhoDensityAir = toCopy.getrhoDensityAir();
        this.specificHeatCapacityAir = toCopy.getspecificHeatCapacityAir();
        this.tau = toCopy.gettau();
        this.tauAlpha = toCopy.gettauAlpha();
        this.isWindVpDefined = toCopy.getisWindVpDefined();

    }
}