[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.4292231.svg)](https://doi.org/10.5281/zenodo.4292231)


AgriculturalModelExchangeInitiative
===================================

Model of Energy Balance of the crop growth simulation model SiriusQuality

Project Organization
--------------------

```

    ├── LICENSE.txt        <- License file
    ├── README.md          <- The top-level README for AMEI members using this project.
    ├── data/              <- data used for model simulation
    │
    ├── doc/               <- Package documentation
    │
    ├── test/              <- model tests for each language and platform
    │
    ├── crop2ml/           <- model units and composite in crop2ml format.
    │      ├── xml files
    │      ├── Algo/
    │
    ├── original/          <- The initial implementation of the model component.
    │
    ├── src/               <- Executable source in different language and platform
    │   ├── pyx/
    │   ├── java/
    │   ├── py/
        ├── R/
        ├── cpp/
        ├── cs/
        ├── f90/
        ├── Bioma/
        ├── Sirius/
        ├── Simplace/
        ├── OpenAlea/
        ├── Record/
        ├── DSSAT/
   
```

Model visualization
-------------------
![](doc/images/energy_balance2.png)

An example of transformation in OpenAlea
----------------------------------------

![](doc/images/openalea_workflow.png)



An example of unit test in R (Penman model unit)
------------------------------------------------

[Penman Jupyter Notebook](https://github.com/Crop2ML-Catalog/SQ_Energy_Balance/tree/master/test/py/Penman.ipynb)


