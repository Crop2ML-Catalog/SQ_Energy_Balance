import  java.io.*;
import  java.util.*;
import java.time.LocalDateTime;

public class EnergyBalanceAuxiliary
{
    private Double minTair;
    private Double maxTair;
    private Double solarRadiation;
    private Double vaporPressure;
    private Double extraSolarRadiation;
    private Double hslope;
    private Double plantHeight;
    private Double wind;
    private Double deficitOnTopLayers;
    private Double VPDair;
    private Double netRadiation;
    private Double netOutGoingLongWaveRadiation;
    private Double netRadiationEquivalentEvaporation;
    private Double energyLimitedEvaporation;
    private Double soilEvaporation;
    
    public EnergyBalanceAuxiliary() { }
    
    public EnergyBalanceAuxiliary(EnergyBalanceAuxiliary toCopy, boolean copyAll) // copy constructor 
    {
        if (copyAll)
        {
            this.minTair = toCopy.getminTair();
            this.maxTair = toCopy.getmaxTair();
            this.solarRadiation = toCopy.getsolarRadiation();
            this.vaporPressure = toCopy.getvaporPressure();
            this.extraSolarRadiation = toCopy.getextraSolarRadiation();
            this.hslope = toCopy.gethslope();
            this.plantHeight = toCopy.getplantHeight();
            this.wind = toCopy.getwind();
            this.deficitOnTopLayers = toCopy.getdeficitOnTopLayers();
            this.VPDair = toCopy.getVPDair();
            this.netRadiation = toCopy.getnetRadiation();
            this.netOutGoingLongWaveRadiation = toCopy.getnetOutGoingLongWaveRadiation();
            this.netRadiationEquivalentEvaporation = toCopy.getnetRadiationEquivalentEvaporation();
            this.energyLimitedEvaporation = toCopy.getenergyLimitedEvaporation();
            this.soilEvaporation = toCopy.getsoilEvaporation();
            this.netRadiation = toCopy.getnetRadiation();
            this.netRadiationEquivalentEvaporation = toCopy.getnetRadiationEquivalentEvaporation();
            this.energyLimitedEvaporation = toCopy.getenergyLimitedEvaporation();
            this.soilEvaporation = toCopy.getsoilEvaporation();
        }
    }
    public Double getminTair()
    { return minTair; }

    public void setminTair(Double _minTair)
    { this.minTair= _minTair; } 
    
    public Double getmaxTair()
    { return maxTair; }

    public void setmaxTair(Double _maxTair)
    { this.maxTair= _maxTair; } 
    
    public Double getsolarRadiation()
    { return solarRadiation; }

    public void setsolarRadiation(Double _solarRadiation)
    { this.solarRadiation= _solarRadiation; } 
    
    public Double getvaporPressure()
    { return vaporPressure; }

    public void setvaporPressure(Double _vaporPressure)
    { this.vaporPressure= _vaporPressure; } 
    
    public Double getextraSolarRadiation()
    { return extraSolarRadiation; }

    public void setextraSolarRadiation(Double _extraSolarRadiation)
    { this.extraSolarRadiation= _extraSolarRadiation; } 
    
    public Double gethslope()
    { return hslope; }

    public void sethslope(Double _hslope)
    { this.hslope= _hslope; } 
    
    public Double getplantHeight()
    { return plantHeight; }

    public void setplantHeight(Double _plantHeight)
    { this.plantHeight= _plantHeight; } 
    
    public Double getwind()
    { return wind; }

    public void setwind(Double _wind)
    { this.wind= _wind; } 
    
    public Double getdeficitOnTopLayers()
    { return deficitOnTopLayers; }

    public void setdeficitOnTopLayers(Double _deficitOnTopLayers)
    { this.deficitOnTopLayers= _deficitOnTopLayers; } 
    
    public Double getVPDair()
    { return VPDair; }

    public void setVPDair(Double _VPDair)
    { this.VPDair= _VPDair; } 
    
    public Double getnetRadiation()
    { return netRadiation; }

    public void setnetRadiation(Double _netRadiation)
    { this.netRadiation= _netRadiation; } 
    
    public Double getnetOutGoingLongWaveRadiation()
    { return netOutGoingLongWaveRadiation; }

    public void setnetOutGoingLongWaveRadiation(Double _netOutGoingLongWaveRadiation)
    { this.netOutGoingLongWaveRadiation= _netOutGoingLongWaveRadiation; } 
    
    public Double getnetRadiationEquivalentEvaporation()
    { return netRadiationEquivalentEvaporation; }

    public void setnetRadiationEquivalentEvaporation(Double _netRadiationEquivalentEvaporation)
    { this.netRadiationEquivalentEvaporation= _netRadiationEquivalentEvaporation; } 
    
    public Double getenergyLimitedEvaporation()
    { return energyLimitedEvaporation; }

    public void setenergyLimitedEvaporation(Double _energyLimitedEvaporation)
    { this.energyLimitedEvaporation= _energyLimitedEvaporation; } 
    
    public Double getsoilEvaporation()
    { return soilEvaporation; }

    public void setsoilEvaporation(Double _soilEvaporation)
    { this.soilEvaporation= _soilEvaporation; } 
    
}