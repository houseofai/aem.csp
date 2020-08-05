**Work in progress**

# Adobe Experience Manager as a Content Science Platform

Integration of Jupyter Notebook into Adobe Experience Manager to allow Data Science with AEM Content.

## Prerequisites

- AEM 6.5 (JDK 11)
- Python 3.6

##### Jupyter Notebook server
- Install Conda: https://docs.conda.io/en/latest/
- Configure and activate your conda environement

```
conda create -n aem python=3.6
conda activate aem
```

- Install Jupyter Notebook and launch it

```
conda install jupyter
jupyter notebook --no-browser
```
Jupyter launches and creates a token to authenticate. Copy this token for the AEM Configuration.

## Configuration

Configure the Content Science OSGI service to link with your Jupyter Notebook server:
- AEM > Tools > Operations > Web Console > OSGI > Configuration
  - org.aem.csp.core.ContentScienceServiceImpl.config

## Run

1. On the main AEM Menu, click on Content Science.
2. Create a Notebook

*To be released: AEM Python Library to interact from Jupyter notebook with AEM Content*
