# jvx.kitchensink

The JVx Kitchensink application is a simple demo application that allows
to test and manipulate most controls, containers and concepts which are
available in the [JVx Enterprise Application Framework][JVx].

# Usage

Simply run it with the provided launcher (JVx KitchenSink*.launch) or build 
the jar and start that.

By default the Swing factory (com.sibvisions.rad.ui.swing.impl.SwingFactory) 
is created and used, to use another factory simply pass the fully qualified 
name of the factory to the main method as argument, e.g.

<pre>
java com.sibvisions.kitchensink.Main com.sibvisions.rad.ui.javafx.impl.FXFactory
</pre>


[JVx]: http://jvx.sibvisions.com
 