

Or if you use Ant 1.6 and later and assuming that Checkstyle is in the library search path, 
then you may use antlib feature of Ant (see http://ant.apache.org/manual/Types/antlib.html 
for more details). For example:

<project name="foo" ...
         xmlns:cs="antlib:com.puppycrawl.tools.checkstyle.ant">
...
  <cs:checkstyle>
  ...
  </cs:checkstyle>
...
</project>

