import  java.io.*;
import  java.util.*;
import java.time.LocalDateTime;

public class EnergyBalanceRate
{
    private Double evapoTranspirationPriestlyTaylor;
    private Double evapoTranspirationPenman;
    private Double evapoTranspiration;
    private Double potentialTranspiration;
    private Double soilHeatFlux;
    private Double cropHeatFlux;
    
    public EnergyBalanceRate() { }
    
    public EnergyBalanceRate(EnergyBalanceRate toCopy, boolean copyAll) // copy constructor 
    {
        if (copyAll)
        {
            this.evapoTranspirationPriestlyTaylor = toCopy.getevapoTranspirationPriestlyTaylor();
            this.evapoTranspirationPenman = toCopy.getevapoTranspirationPenman();
            this.evapoTranspiration = toCopy.getevapoTranspiration();
            this.potentialTranspiration = toCopy.getpotentialTranspiration();
            this.soilHeatFlux = toCopy.getsoilHeatFlux();
            this.cropHeatFlux = toCopy.getcropHeatFlux();
            this.evapoTranspirationPriestlyTaylor = toCopy.getevapoTranspirationPriestlyTaylor();
            this.evapoTranspirationPenman = toCopy.getevapoTranspirationPenman();
            this.evapoTranspiration = toCopy.getevapoTranspiration();
            this.potentialTranspiration = toCopy.getpotentialTranspiration();
            this.soilHeatFlux = toCopy.getsoilHeatFlux();
            this.cropHeatFlux = toCopy.getcropHeatFlux();
        }
    }
    public Double getevapoTranspirationPriestlyTaylor()
    { return evapoTranspirationPriestlyTaylor; }

    public void setevapoTranspirationPriestlyTaylor(Double _evapoTranspirationPriestlyTaylor)
    { this.evapoTranspirationPriestlyTaylor= _evapoTranspirationPriestlyTaylor; } 
    
    public Double getevapoTranspirationPenman()
    { return evapoTranspirationPenman; }

    public void setevapoTranspirationPenman(Double _evapoTranspirationPenman)
    { this.evapoTranspirationPenman= _evapoTranspirationPenman; } 
    
    public Double getevapoTranspiration()
    { return evapoTranspiration; }

    public void setevapoTranspiration(Double _evapoTranspiration)
    { this.evapoTranspiration= _evapoTranspiration; } 
    
    public Double getpotentialTranspiration()
    { return potentialTranspiration; }

    public void setpotentialTranspiration(Double _potentialTranspiration)
    { this.potentialTranspiration= _potentialTranspiration; } 
    
    public Double getsoilHeatFlux()
    { return soilHeatFlux; }

    public void setsoilHeatFlux(Double _soilHeatFlux)
    { this.soilHeatFlux= _soilHeatFlux; } 
    
    public Double getcropHeatFlux()
    { return cropHeatFlux; }

    public void setcropHeatFlux(Double _cropHeatFlux)
    { this.cropHeatFlux= _cropHeatFlux; } 
    
}