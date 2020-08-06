
# Adobe Experience Manager as a Content Science Platform (AEM.CSP)

Integration of Jupyter Notebook into Adobe Experience Manager to allow Data Science with AEM Content.

## Prerequisites

- AEM 6.5 (JDK 11)
- Python 3.6
- Clone this repository
```
git clone https://github.com/OdysseeT/aem.csp.git
```

##### AEMpy (Python library for AEM)
```
pip install git+https://github.com/OdysseeT/aempy.git
```

##### Jupyter Notebook server


- Install Conda: https://docs.conda.io/en/latest/
- Configure and activate your conda environement

```
conda create -n aem python=3.6
conda activate aem
```

- Install Jupyter Notebook and launch it

```
# Install Jupyter
conda install jupyter

# Switch to folder with Jupyter start script and config file
cd aem.csp/jupyter

# Start the Jupyter script
sh jupyter_start.sh
```
Jupyter launches and creates a token to authenticate. Copy this token for the AEM Configuration.

## Configuration

Configure the Content Science OSGI service to link with your Jupyter Notebook server:
- AEM > Tools > Operations > Web Console > OSGI > Configuration
  - org.aem.csp.core.ContentScienceServiceImpl.config

## Run

1. On the main AEM Menu, click on Content Science.
2. Create a Notebook
3. Enjoy
