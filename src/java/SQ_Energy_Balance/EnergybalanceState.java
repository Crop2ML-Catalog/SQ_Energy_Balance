import  java.io.*;
import  java.util.*;
import java.time.LocalDateTime;
public class EnergyBalanceState
{
    private Double diffusionLimitedEvaporation;
    private Double conductance;
    private Double minCanopyTemperature;
    private Double maxCanopyTemperature;
    
    public EnergyBalanceState() { }
    
    public EnergyBalanceState(EnergyBalanceState toCopy, boolean copyAll) // copy constructor 
    {
        if (copyAll)
        {
            this.diffusionLimitedEvaporation = toCopy.getdiffusionLimitedEvaporation();
            this.conductance = toCopy.getconductance();
            this.minCanopyTemperature = toCopy.getminCanopyTemperature();
            this.maxCanopyTemperature = toCopy.getmaxCanopyTemperature();
        }
    }
    public Double getdiffusionLimitedEvaporation()
    { return diffusionLimitedEvaporation; }

    public void setdiffusionLimitedEvaporation(Double _diffusionLimitedEvaporation)
    { this.diffusionLimitedEvaporation= _diffusionLimitedEvaporation; } 
    
    public Double getconductance()
    { return conductance; }

    public void setconductance(Double _conductance)
    { this.conductance= _conductance; } 
    
    public Double getminCanopyTemperature()
    { return minCanopyTemperature; }

    public void setminCanopyTemperature(Double _minCanopyTemperature)
    { this.minCanopyTemperature= _minCanopyTemperature; } 
    
    public Double getmaxCanopyTemperature()
    { return maxCanopyTemperature; }

    public void setmaxCanopyTemperature(Double _maxCanopyTemperature)
    { this.maxCanopyTemperature= _maxCanopyTemperature; } 
    
}