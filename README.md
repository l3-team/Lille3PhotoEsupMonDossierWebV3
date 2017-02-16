Java class client for use with RefPhoto
---


Pre-requisites :
---
- have a RefPhoto instance installed (https://github.com/l3-team/RefPhoto)
- have esup-mon-dossier-web v3 installed (https://www.esup-portail.org/wiki/display/PROJMONDOSSIERWEB/1+-+MonDossierWeb+v3)

Installation :
---
- just copy the file Lille3Photo.java in folder : src/main/java/fr/univlorraine/mondossierweb/photo
- then configure the file src/main/webapp/META-INF/context.xml, simple adjust the url tokenurl, imageurl and binaryurl :
```
...
        <Parameter name="serveurphoto.implementation" value="Lille3Photo" />
        <Parameter name="param.photoserver.tokenurl" value="https://refphotos.univ.fr/tokenEtu/add/" />
        <Parameter name="param.photoserver.imageurl" value="https://refphotos.univ.fr/image/" />
        <Parameter name="param.photoserver.binaryurl" value="https://refphotos.univ.fr/binaryEtu/" />
...
```

Have fun!
