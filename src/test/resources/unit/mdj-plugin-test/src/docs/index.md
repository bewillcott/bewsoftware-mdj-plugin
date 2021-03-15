@@@
use: articles2
title: ${document.name}
@@@

# ${document.name}

---

It is advisable that the first file you create be this file.  Since the final
'jar' file will contain a stand-alone http server, it will be looking for
the `index.html` file to serve up first.

This however is **not** a requirement.  If you want your end-users to get a
listing of the files in the 'jar' file, then you don't need this file.

~~~
   @Override
   public static String toString(){
       return "This is the text";
   }
~~~

- [ ] An unchecked mutable checkbox.
- [ ]! An unchecked immutable checkbox.
- [x] A checked mutable checkbox.
- [X]! A checked immutable checkbox.

Some junk.

- [ ][@myClass] An unchecked mutable checkbox with the class attribute: `myClass`.
- [ ]![@myClass2] An unchecked immutable checkbox with the class attribute: `myClass2`.
- [x][@yourClass] A checked mutable checkbox with the class attribute: `yourClass`.
- [X]![@bestClass] A checked immutable checkbox with the class attribute: `bestClass`.

@@@[#navbar]
- [@active] [Home](#)
- [@right] [About]

[About]:About.html
[Home]:index.html
@@@
