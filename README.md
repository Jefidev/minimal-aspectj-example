# Aspectj investigation

Comment fonctionne AspectJ et comment effectuer des captures dans du bytecode contenus dans les dépendances d'un projet ?

## AspectJ fonctionnement

AspectJ permet de faire de la programmation orienté aspect en Java. C'est un type de programmation qui permet d'injecter des comportements dans du code existant. Un Aspect défini un point de coupe c'est à dire un type de fonctions qui seront impactées par l'aspect. Suite à ce point de coupe l'utilisateur d'aspectJ peut définir le comportement de l'aspect et quand celui-ci sera activé (à l'appel de la fonction ou après).

### Compilation d'Aspect, Bytecode et autres merdes du style

Afin d'injecter ces comportements dans le code java. AspectJ utilise une technique appelée le [*Bytecode weaving*](https://www.eclipse.org/aspectj/doc/released/devguide/bytecode-concepts.html). Ce processus ne peut pas être réalisé par le compilateur Java c'est pourquoi AspectJ propose un nouveau compilateur appelé [ajc](https://www.eclipse.org/aspectj/doc/released/devguide/ajc-ref.html) qui va s'occuper du Bytecode weaving. 

Pour compiler des aspects, ajc a besoin, tout comme le compilateur java, de connaitre le *classpath* du projet. Le classpath contient les dépendances du projet dans des fichiers jar ou zip ou alors directement un dossier contenant du code java. Pour que AspectJ fonctionne il faut que le jar [aspectjrt.jar](https://mvnrepository.com/artifact/org.aspectj/aspectjrt) soit dans le classpath. Ajc introduit aussi l'*aspectpath* qui est l'équivalent du classpath mais pour les aspects. Dans ce dossier l'utilisateur pourra placer du code d'aspect déjà compilé au format jar, zip ou simplement une arborescence de fichiers. Ces aspects seront utilisés lors de la compilation du programme.
Finalement, ajc se base aussi sur un *inpath* qui peut lui aussi contenir des archives et du code source. Le inpath permet de définir quelles sont les fichiers sources qui doivent être touchés par le Bytecode weaving.

Maintenant qu'on sait ça, la question est : Comment cette compilation s'intègre dans le workflow de compilation java par défaut ?

### Compiler de l'aspectJ komenkonfet ?

Le processus de weaving propre aux aspects peut avoir lieu à trois moments différents 
- compile-time : The simplest approach, ajc will be called during the compilation to generate class binary containing the aspect logic
- post-compile : Apply the aspect to exising class binary. Useful to add aspect to existing jar or dependencies
- load-time : Delay the application of the aspect at runtime. Complex.


# Pistes
https://github.com/apache/spark/blob/master/sql/core/src/main/scala/org/apache/spark/sql/execution/datasources/jdbc/JDBCRDD.scala
# Ressources

https://www.eclipse.org/aspectj/doc/released/index.html#paths