# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.

Cette mini-application à été développée sur l'IDE Android Studio 3.6
Langage : JAVA (8).
Dépendances : Gradle - EventBus - ButterKnife... (retrouvez l'intégralité des dépendances en pied de page).
compileSdkVersion : 28
minSdkVersion : 21
1ère version du code

>> Pour télécharger l'application: 
- Cliquez sur l'URL suivante : github.com/Angiedevdev/Entrevoisins ;
- Cliquez ensuite sur le bouton vert "clone or download" ;
- Dézippez-le et classez-le dans le dossier de votre choix ;


>> Pour ouvrir le dossier :
- Ouvrez Android Studio
- Via le menu, sélectionnez l'option Ouvrir et cliquez sur le fichier Entrevoisins.


>> Pour faire fonctionner l'application :
Dans le package Gradle Scripts, fichier build.gradle(Module:app) vous trouverez les informations nécessaires au bon fonctionnement de l'application sur Emulateur ou sur un device externe.
En cas de perte, pour rappel, les dépendances suivantes ont été implémentées :

(ci-dessous, extrait du fichier build.gradle(Module:app))

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:recyclerview-v7:28.0.0'

    implementation 'com.jakewharton:butterknife:9.0.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:9.0.0'

    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'

    // EVENT BUS
    implementation 'org.greenrobot:eventbus:3.1.1'

    // UNIT TEST
    testImplementation 'junit:junit:4.12'
    testImplementation 'org.hamcrest:java-hamcrest:2.0.0.0'
    // INSTRUMENTATION TEST
    androidTestImplementation 'com.android.support.test:rules:1.0.2'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:3.0.2'
}
