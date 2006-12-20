package femProject.Neumann;

import femProject.Function.Function;
import femProject.Function.FunctionInterface;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub
 * Date: 2006-12-15
 * Time: 16:14:48
 * To change this template use File | Settings | File Templates.
 */
public class Neumann {
/*
program mes1_1np;

{ Program rozwiazuje metoda elementu skonczonego rownanie
   -(p(x)u')'+ q(x)u'+ r(x)u = f(x)  dla xî(a;b)
 z warunkami brzegowymi Neumana u'(a)=upa, u'(b)=upb,
 z zastosowaniem elementow liniowych.
 Program porownuje rozwiazanie otrzymane z metody elementu
 skonczonego z rozwiazaniem dokladnym u(x).
 UWAGA : Funkcje p(x),q(x),r(x),f(x) oraz u(x) nalezy podac w podprogramach
         function o tych samych nazwach}

uses crt,graph;
const nmax=1001;
      sc='c:\devel\tp\bgi';
type tab=array[0..nmax] of real;
var n,i,ig,jg:integer;
    a,b,upa,upb,h:real;
    z:char;
    bb:boolean;
    xi,aa,ab,ac,af,ax:tab;
*/
    /*
{**********************************************************}

function p(x:real):real;
begin
 p:=1
end{p};

function q(x:real):real;
begin
 q:=0
end{q};

function r(x:real):real;
begin
 r:=1
end{r};

function f(x:real):real;
begin
  f:=0
end{f};

function u(x:real):real;
begin
 u:=0
end{u};
*/

    private Function p, q, r, f, u;

    private int nmax = 1000;
    private int n;
    private float a, b, upa, upb, h;
    private boolean bb;
    private float[] xi, aa, ab, ac, af, ax;

    public Neumann(float a, float b, int n, float upa, float upb,  boolean inputU) throws Exception{

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        FunctionInterface functionInterfacep = new FunctionInterface();
        int posX = (int) (dim.getWidth() / 2) - functionInterfacep.getWidth() / 2,
                        posY = (int) (dim.getHeight() / 2) - functionInterfacep.getHeight() / 2;
        
        functionInterfacep.setLocation(posX, posY);
        functionInterfacep.setFunctionName("p");
        functionInterfacep.setVisible(true);
        p = functionInterfacep.getFunction();
        if(p==null) throw new Exception("Nie wprowadzono funkcji p");

        FunctionInterface functionInterfaceq = new FunctionInterface();
        functionInterfaceq.setLocation(posX, posY);
        functionInterfaceq.setFunctionName("q");
        functionInterfaceq.setVisible(true);
        q = functionInterfaceq.getFunction();
        if(q==null) throw new Exception("Nie wprowadzono funkcji q");

        FunctionInterface functionInterfacer = new FunctionInterface();
        functionInterfacer.setLocation(posX, posY);
        functionInterfacer.setFunctionName("r");
        functionInterfacer.setVisible(true);
        r = functionInterfacer.getFunction();
        if(r==null) throw new Exception("Nie wprowadzono funkcji r");

        FunctionInterface functionInterfacef = new FunctionInterface();
        functionInterfacef.setLocation(posX, posY);
        functionInterfacef.setFunctionName("f");
        functionInterfacef.setVisible(true);
        f = functionInterfacef.getFunction();
        if(f==null) throw new Exception("Nie wprowadzono funkcji f");

        if(inputU)
        {
        FunctionInterface functionInterfaceu = new FunctionInterface();
        functionInterfaceu.setLocation(posX, posY);
        functionInterfaceu.setFunctionName("u");
        functionInterfaceu.setVisible(true);
        u = functionInterfaceu.getFunction();
        if(u==null) throw new Exception("Nie wprowadzono funkcji u");
        }
        else
        u = null;

        this.a = a;
        this.b = b;
        this.n = n;
        this.upa = upa;
        this.upb = upb;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getUpa() {
        return upa;
    }

    public void setUpa(float upa) {
        this.upa = upa;
    }

    public float getUpb() {
        return upb;
    }

    public void setUpb(float upb) {
        this.upb = upb;
    }


    public float[] getY() {
        float[] y = new float[n+1];
        bb = false;
        for(int i=0; i < n+1; i++)
            y[i]=this.uu(xi[i]);
        return y;
    }

    public float[][] getU(int count) throws Exception{
        float[][] y = new float[2][];
        y[0] = new float[count];
        y[1] = new float[count];
        float step = (this.u.getMaxX() - this.u.getMinX())/count;
        float min = this.u.getMinX();
        for(int i=0; i < count-1; i++){
            y[0][i] = min+i*step;
            y[1][i]=this.u.getValue(y[0][i]);
        }
        y[0][count-1]=this.u.getMaxX();
        y[1][count-1]=this.u.getValue(u.getMaxX());
        return y;
    }

    public float[] getX() {
        return xi;
    }

        public float getU(float v) throws Exception {
        return u.getValue(v);
    }
/*
    {**********************************************************}

    function v(i:integer;x:real):real;
    {Funkcje bazowe}
    begin
     if i=0 then v:=(a-x)/h+1 else if i=n then v:=(x-b)/h+1 else
     if (x>=xi[i-1]) and (x<=xi[i]) then v:=(x-xi[i-1])/h
     else if (x>=xi[i]) and (x<=xi[i+1]) then v:=-(x-xi[i+1])/h
     else v:=0
    end{v};
    */
    private float v(int i, float x) {
        if (i == 0) return (a - x) / h + 1;
        else if (i == n) return (x - b) / h + 1;
        else if (x >= xi[i - 1] && x <= xi[i]) return (x - xi[i - 1]) / h;
        else if (x >= xi[i] && x <= xi[i + 1]) return -(x - xi[i + 1]) / h;
        else return 0;
    }

    /*
function dv(i:integer;x:real):real;
{Pochodna funkcji bazowych}
begin
 if i=0 then dv:=-1/h else if i=n then dv:=1/h else
 if (x>=xi[i-1]) and (x<xi[i]) then dv:=1/h
 else if (x>xi[i]) and (x<=xi[i+1]) then dv:=-1/h
 else dv:=0
end{dv};
 */

    private float dv(int i, float x) {
        if (i == 0) return -1 / h;
        else if (i == n) return 1 / h;
        else if (x >= xi[i - 1] && x < xi[i]) return 1 / h;
        else if (x > xi[i] && x <= xi[i + 1]) return -1 / h;
        else return 0;
    }

    /*    function uu(x:real):real;
   {Rozwiazanie przyblizone}
   var i:integer;
   begin
     i:=trunc((x-a)/h);
     if i=n then uu:=ax[n] else
      uu:=((ax[i+1]-ax[i])*x+xi[i+1]*ax[i]-xi[i]*ax[i+1])/h
   end{uu};
    */
    private float uu(float x) {
        int i = (int) Math.floor((x - a) / h);
        if (i == n) return ax[n];
        else return ((ax[i + 1] - ax[i]) * x + xi[i + 1] * ax[i] - xi[i] * ax[i + 1]) / h;
    }
    /*
function uuu(x:real):real;
begin
 if bb then uuu:=u(x) else uuu:=uu(x)
end{uuu};
*/

    private float uuu(float x) throws Exception {
        if (bb) return u.getValue(x);
        else return uu(x);
    }

    /*
function s(i,j:integer;x:real):real;
begin
 s:=p(x)*dv(i,x)*dv(j,x)+q(x)*dv(i,x)*v(j,x)+r(x)*v(i,x)*v(j,x)
end{s};

    */
    private float s(int i, int j, float x) throws Exception {
        return p.getValue(x) * dv(i, x)* dv(j, x) + q.getValue(x) * dv(i, x) * v(j, x) + r.getValue(x) * v(i, x) * v(j, x);
    }

    /*
function g(k,i,j:integer;x:real):real;
begin
 if k=0 then g:=s(i,j,x)
 else g:=f(x)*v(i,x)
end{g};
*/

    private float g(int k, int i, int j, float x) throws Exception {
        if (k == 0) return s(i, j, x);
        else return f.getValue(x) * v(i, x);
    }

    /*
    function calka(k,i,j:integer;alfa,beta:real):real;
    {Kwadratura Gaussa-Lagrange'a oparta na 2 punktach}
    var l:integer;
        tau,s,t,a1,a2:real;
    begin
     tau:=(beta-alfa)/2;
     s:=(beta+alfa)/2;
     t:=tau*sqrt(3)/3;
     a1:=s-t;
     a2:=s+t;
     calka:=tau*(g(k,i,j,a1)+g(k,i,j,a2));
    end;
    */
    private float calka(int k, int i, int j, float alfa, float beta) throws Exception {
        int l;
        float tau = (beta - alfa) / 2;
        float s = (beta + alfa) / 2;
        float t = (float) (tau * Math.sqrt(3.0) / 3);
        float a1 = s - t;
        float a2 = s + t;
        return tau * (g(k, i, j, a1) + g(k, i, j, a2));
    }

    private float calka1(float alfa, float beta) throws Exception {
        int l;
        float tau = (beta - alfa) / 2;
        float s = (beta + alfa) / 2;
        float t = (float) (tau * Math.sqrt(3.0) / 3);
        float a1 = s - t;
        float a2 = s + t;
        return tau * (g1(a1) + g1(a2));
    }

    private float g1(float x) throws Exception{
         return (float)Math.sqrt(Math.abs(u.getValue(x)-uu(x)));
    }

    /*
procedure trojdiag(n:integer;var a,b,c,f,x:tab);
{   Metoda eliminacji Gaussa dla macierzy      }
{       trojdiagonalnej (progonka)             }
{      n -liczba niewiadomych                  }
{      a[1..n] - glowna przekatna              }
{      b[2..n] - elementy pod przekatna        }
{      c[1..n-1] - elementy nad przekatna      }
{      f[1..n] - wektor prawych stron          }
{      x[1..n] -wektor rozwiazan               }

var k:integer;
    m:real;
begin
 for k:=2 to n do
 begin
  m:=b[k]/a[k-1];
  a[k]:=a[k]-m*c[k-1];
  f[k]:=f[k]-m*f[k-1]
 end;
 x[n]:=f[n]/a[n];
 for k:=n-1 downto 1 do
  x[k]:=(f[k]-c[k]*x[k+1])/a[k];
end{trojdiag};
*/


    private void trojdiag(int n, float[] a, float[] b, float[] c, float[] f, float[] x) {
        float m;

        for (int k = 2; k <= n; k++) {
            m = b[k] / a[k - 1];
            a[k] -= m * c[k - 1];
            f[k] -= m * f[k - 1];
        }
        x[n] = f[n] / a[n];
        for (int k = n - 1; k >= 1; k--) {
            x[k] = (f[k] - c[k] * x[k + 1]) / a[k];
        }

/*        for (int k = 1; k <= n; k++) {
            m = b[k] / a[k - 1];
            a[k] -= m * c[k - 1];
            f[k] -= m * f[k - 1];
        }
        x[n] = f[n] / a[n];
        for (int k = n - 1; k >= 0; k--) {
            x[k] = (f[k] - c[k] * x[k + 1]) / a[k];
        }
*/    }

    /*
   for i:=0 to n-1 do xi[i]:=a+i*h;
   xi[n]:=b;

   {Obliczanie wspolczynnikow macierzy}
   for i:=1 to n do ab[i]:=calka(0,i-1,i,xi[i-1],xi[i]);
   aa[0]:=calka(0,0,0,xi[0],xi[1]);
   for i:=1 to n-1 do aa[i]:=calka(0,i,i,xi[i-1],xi[i])
                            +calka(0,i,i,xi[i],xi[i+1]);
   aa[n]:=calka(0,n,n,xi[n-1],xi[n]);
   for i:=0 to n-1 do ac[i]:=calka(0,i+1,i,xi[i],xi[i+1]);

   {Obliczanie prawych stron}
   af[0]:=-p(a)*upa+calka(1,0,0,xi[0],xi[1]);
   for i:=1 to n-1 do af[i]:=calka(1,i,i,xi[i-1],xi[i])
                            +calka(1,i,i,xi[i],xi[i+1]);
   af[n]:=p(b)*upb+calka(1,n,n,xi[n-1],xi[n]);

   for i:=n+1 downto 1 do
   begin
    ab[i]:=ab[i-1];
    aa[i]:=aa[i-1];
    ac[i]:=ac[i-1];
    af[i]:=af[i-1]
   end;

   {Rozwiazanie ukladu rownan}
   trojdiag(n+1,aa,ab,ac,af,ax);

   for i:=1 to n+1 do ax[i-1]:=ax[i];

   clrscr;
   write('NR WEZLA    WARTOSC x     ROZ.DOKLADNE    ROZ.PRZYBLIZONE     BLAD');
   window(2,3,79,25);
   clrscr;
   for i:=0 to n do
   begin
    write(i:5,'    ',xi[i]:10:6,'      ',u(a+i*h):10:6);
    writeln('       ',uu(a+i*h):10:6,'      ',u(a+i*h)-uu(a+i*h):12);
    if ((i+1) div 20*20=i+1) or (i=n) then ENTER
   end;

    */
    private void tabInit() {
        xi = new float[n + 2];
        aa = new float[n + 2];
        ab = new float[n + 2];
        ac = new float[n + 2];
        af = new float[n + 2];
        ax = new float[n + 2];
    }

    public void start()  throws Exception{
        this.tabInit();
        h = (b - a) / n;

        for (int i = 0; i <= n-1; ++i)
            xi[i] = a + i * h;
        xi[n] = b;

        //try {
            //{Obliczanie wspolczynnikow macierzy}
            for (int i = 1; i <= n; ++i)
                ab[i] = calka(0, i - 1, i, xi[i - 1], xi[i]);
            aa[0] = calka(0, 0, 0, xi[0], xi[1]);
            for (int i = 1; i <= n - 1; ++i)
                aa[i] = calka(0, i, i, xi[i - 1], xi[i]) + calka(0, i, i, xi[i], xi[i + 1]);
            aa[n] = calka(0, n, n, xi[n - 1], xi[n]);
            for (int i = 0; i <= n - 1; ++i)
                ac[i] = calka(0, i + 1, i, xi[i], xi[i + 1]);

            //{Obliczanie prawych stron}
            af[0] = -p.getValue(a) * upa + calka(1, 0, 0, xi[0], xi[1]);
            for(int i=1;i<=n-1;++i)
              af[i]=calka(1,i,i,xi[i-1],xi[i])+calka(1,i,i,xi[i],xi[i+1]);
            af[n]=p.getValue(b)*upb+calka(1,n,n,xi[n-1],xi[n]);

            //co to za dziwne cofanie indeksow
            for(int i=n+1;i>=1;--i){
                ab[i]=ab[i-1];
                aa[i]=aa[i-1];
                ac[i]=ac[i-1];
                af[i]=af[i-1];
            }
            //{Rozwiazanie ukladu rownan}
            //jaka kicha - jak tutaj daje n+1 to sie nawet psuje
            trojdiag(n+1,aa,ab,ac,af,ax);
            for(int i=1;i<=n+1;++i)
                ax[i-1]=ax[i];
        //} catch (Exception ex) {
        //}*
    }
    public float error() throws Exception{
        int n3 = 0;
        if(n<=33)
            n3 = 100;
        else
            n3 = 3*n;
        float h3 = ((b-a)/n3);
        float bs = 0;
        for(int i=0;i<n3;++i){
            float x3 = a+i*h3;
            bs += calka1(x3, x3+h3);
        }
        return (float)Math.sqrt(Math.abs(bs));
    }

}
  /*
procedure ENTER;
begin
 writeln;
 write('Wcisnij ENTER');
 readln;
 writeln
end{ENTER};

procedure czytint(var n:integer; s:string);
begin
  write(s,'=');
  readln(n)
end{czytin};

procedure czytreal(var x:real; s:string);
begin
  write(s,'=');
  readln(x)
end{czytreal};

procedure piszwek(n:integer; w:tab; s:string);
var i:integer;
begin
 writeln;
 for i:=1 to n do
 begin
  writeln(s,'[',i:2,']=',w[i]:12:6);
  if (i div 20*20=i) or (i=n) then ENTER
 end
end{piszwek};

procedure wykres(a,b:real;x1,y1,x2,y2:integer);
{ Procedura rysuje wykres funkcji u(x) w przedziale [a;b] }
{ w oknie (x1,y1),(x2,y2) }
var nx,ny,i:integer;
    min,max,h,y,w:real;
begin
 setviewport(x1,y1,x2,y2,true);
 if bb then clearviewport;
 nx:=x2-x1;
 ny:=y2-y1;
 rectangle(0,0,nx,ny);
 h:=(b-a)/nx;
 min:=uuu(a);
 max:=uuu(a);
 for i:=1 to nx do
 begin
  y:=uuu(a+i*h);
  if min>y then min:=y;
  if max<y then max:=y
 end;
 if max=min then
 line(1,ny div 2,nx-1,ny div 2)
 else
 begin
  w:=ny/(max-min);
  for i:=0 to nx-1 do
  line(i,ny-trunc(w*(uuu(a+i*h)-min)),i+1,ny-trunc(w*(uuu(a+(i+1)*h)-min)))
 end
end{wykres};


BEGIN {Poczatek programu}

repeat
window(1,1,80,25);
textbackground(1);
textcolor(14);
clrscr;
writeln;
writeln('       PROGRAM ROZWIAZUJE METODA ELEMENTU SKONCZONEGO ROWNANIE');
writeln('               -(p(x)u'')''+ q(x)u''+ r(x)u = f(x)');
writeln('          Z NIEJEDNORODNYMI WARUNKAMI BRZEGOWYMI NEUMANA');
writeln('                 Z ZASTOSOWANIEM ELEMENTOW LINIOWCH');
writeln('       PROGRAM POROWNUJE ROZWIAZANIE DOKLADNE Z PRZYBLIZONYM');
writeln;
writeln(' Podaj przedzial,w ktorym chcesz rozwiazac rownanie:');
czytreal(a,' a');
czytreal(b,' b');
writeln(' Podaj wartosci brzegowe pochodnych:');
czytreal(upa,' u''(a)');
czytreal(upb,' u''(b)');
writeln(' Podaj liczbe podzialow odcinka [a,b] (n<=1000):');
czytint(n,' n');

h:=(b-a)/n;

for i:=0 to n-1 do xi[i]:=a+i*h;
xi[n]:=b;

{Obliczanie wspolczynnikow macierzy}
for i:=1 to n do ab[i]:=calka(0,i-1,i,xi[i-1],xi[i]);
aa[0]:=calka(0,0,0,xi[0],xi[1]);
for i:=1 to n-1 do aa[i]:=calka(0,i,i,xi[i-1],xi[i])
                         +calka(0,i,i,xi[i],xi[i+1]);
aa[n]:=calka(0,n,n,xi[n-1],xi[n]);
for i:=0 to n-1 do ac[i]:=calka(0,i+1,i,xi[i],xi[i+1]);

{Obliczanie prawych stron}
af[0]:=-p(a)*upa+calka(1,0,0,xi[0],xi[1]);
for i:=1 to n-1 do af[i]:=calka(1,i,i,xi[i-1],xi[i])
                         +calka(1,i,i,xi[i],xi[i+1]);
af[n]:=p(b)*upb+calka(1,n,n,xi[n-1],xi[n]);

for i:=n+1 downto 1 do
begin
 ab[i]:=ab[i-1];
 aa[i]:=aa[i-1];
 ac[i]:=ac[i-1];
 af[i]:=af[i-1]
end;

{Rozwiazanie ukladu rownan}
trojdiag(n+1,aa,ab,ac,af,ax);

for i:=1 to n+1 do ax[i-1]:=ax[i];

clrscr;
write('NR WEZLA    WARTOSC x     ROZ.DOKLADNE    ROZ.PRZYBLIZONE     BLAD');
window(2,3,79,25);
clrscr;
for i:=0 to n do
begin
 write(i:5,'    ',xi[i]:10:6,'      ',u(a+i*h):10:6);
 writeln('       ',uu(a+i*h):10:6,'      ',u(a+i*h)-uu(a+i*h):12);
 if ((i+1) div 20*20=i+1) or (i=n) then ENTER
end;

writeln;
writeln('CZY RYSOWAC WYKRESY ROZWIAZAN ? (T/N)');
z:=readkey;
if (z='t') or (z='T') then
begin
 ig:=detect;
 initgraph(ig,jg,sc);
 setbkcolor(1);
 setcolor(15);
 settextstyle(0,0,1);
 setlinestyle(0,0,1);
 bb:=true;
 wykres(a,b,10,10,getmaxx-10,getmaxy-10);
 outtextxy(100,10,'ROZWIAZANIE DOKLADNE - wcisnij Enter');
 readln;
 setlinestyle(0,0,1);
 setcolor(2);
 bb:=false;
 wykres(a,b,10,10,getmaxx-10,getmaxy-10);
 outtextxy(100,30,'ROZWIAZANIE PRZYBLIZONE - wcisnij Enter');
 readln;
 closegraph
end;

writeln;
writeln;
writeln('CZY JESZCZE RAZ ? (T/N)');
z:=readkey;
until (z='n') or (z='N')

END{Koniec programu}.
*/