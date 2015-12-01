# swd-interpreter
Parser and interpreter of a simple mathcad-like language

1. (Done) Support parsing and evaluation of simple arithmetic expressions:
--------------------------------------------------------------------------

       (2 + 1) + (12)

Expression grammar is defined the following way:

        Expr -> Const | (Expr) | Expr + Expr
        Const -> \d+

2. Support variables
--------------------
        var x = 234 + 45
        var a = x + 12

Updated grammar:
        
        Expr -> Const | Var | (Expr) | Expr + Expr
        Var -> \w+
        Assign -> var Var = Expr 
        Stmt = Expr | Assign
        
3. Support scoping
------------------

        var x = 2 + 6 // x == 8 
            var y = x + 8 // new scope, y == 16
            var x = 0 // local scoped x
            var q = x + 7 // q == 7
        var z = x + 1 //z == 9

While interpreting, program should print every var value daclaration

Write unit tests!
