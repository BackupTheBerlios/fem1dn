package femProject.Dirichlet;

import femProject.Function.Function;
import femProject.Function.FunctionInterface;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: zagi
 * Date: 2006-12-07
 * Time: 18:20:48
 * To change this template use File | Settings | File Templates.
 */
public class Dirichlet {
    Function p,q,r,f,pp;

    final int nmax=1000;


    private int n,i,ig,jg;
    private float a,b,ua,ub,h;
    private char z;
    private float[] xi,aa,ab,ac,af,ax;

    public Dirichlet() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        FunctionInterface functionInterfacep = new FunctionInterface();
        int     posX = (int) (dim.getWidth() / 2) - functionInterfacep.getWidth() / 2,
                posY = (int) (dim.getHeight() / 2) - functionInterfacep.getHeight() / 2;
        functionInterfacep.setLocation(posX,posY);

        functionInterfacep.setFunctionName("p");
        functionInterfacep.setVisible(true);
        p = functionInterfacep.getFunction();
      
        FunctionInterface functionInterfacepp = new FunctionInterface();
        functionInterfacepp.setLocation(posX,posY);
        functionInterfacepp.setFunctionName("pp");
        functionInterfacepp.setVisible(true);
        pp = functionInterfacepp.getFunction();

        FunctionInterface functionInterfaceq = new FunctionInterface();
        functionInterfaceq.setLocation(posX,posY);
        functionInterfaceq.setFunctionName("q");
        functionInterfaceq.setVisible(true);
        q = functionInterfaceq.getFunction();


        FunctionInterface functionInterfacer = new FunctionInterface();
        functionInterfacer.setLocation(posX,posY);
        functionInterfacer.setFunctionName("r");
        functionInterfacer.setVisible(true);
        r = functionInterfacer.getFunction();


        FunctionInterface functionInterfacef = new FunctionInterface();
        functionInterfacef.setLocation(posX,posY);
        functionInterfacef.setFunctionName("f");
        functionInterfacef.setVisible(true);
        f = functionInterfacef.getFunction();
    }
    private float v(int i, float x){
        if(i==0) return  (a-x)/h+1;
        else if (i==n) return (x-b)/h+1;
        else if( x>=xi[i-1] && x <= xi[i]) return (x-xi[i-1])/h;
        else if(x>=xi[i] && x <=xi[i+1]) return -(x-xi[i+1])/h;
        else return 0;
    }
   /*
    function v(i:integer;x:real):real;
    {Funkcje bazowe}
    begin
     if i=0 then v:=(a-x)/h+1 else if i=n then v:=(x-b)/h+1 else
     if (x>=xi[i-1]) and (x<=xi[i]) then v:=(x-xi[i-1])/h
     else if (x>=xi[i]) and (x<=xi[i+1]) then v:=-(x-xi[i+1])/h
     else v:=0
    end{v};
    */
    private float dv(int i, float x){
       if(i==0)          return -1/h;
       else if(i==n)    return 1/h;
       else if(x>=xi[i-1] && x<xi[i]) return 1/h;
       else if(x>xi[i] && x<=xi[i+1]) return -1/h;
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
    private float uu(float x){
        int i = (int)Math.floor((x-a)/h);
        if(i==n) return ax[n];
        else return ((ax[i+1]-ax[i])*x+xi[i+1]*ax[i]-xi[i]*ax[i+1])/h;
    }
    /*
    function uu(x:real):real;
    var i:integer;
    begin
     i:=trunc((x-a)/h);
     if i=n then uu:=ax[n] else
      uu:=((ax[i+1]-ax[i])*x+xi[i+1]*ax[i]-xi[i]*ax[i+1])/h
    end{uu};
    */

    private float s(int i, int j, float x) throws Exception {
        return p.getValue(x)*dv(i,x) + q.getValue(x)*dv(i,x) *v(j,x)+r.getValue(x)*v(i,x)*v(j,x);
    }
    /*
    function s(i,j:integer;x:real):real;
    begin
     s:=p(x)*dv(i,x)*dv(j,x)+q(x)*dv(i,x)*v(j,x)+r(x)*v(i,x)*v(j,x)
    end{s};

    */

    private float w(float x){
        return ((ub-ua)*x+b*ua-a*ub)/(b-a);
    }
    /*
    function w(x:real):real;
    {Funkcja przesuniecia}
    begin
     w:=((ub-ua)*x+b*ua-a*ub)/(b-a)
    end{w};
    */
    private float ff(float x) throws Exception {
        return f.getValue(x)+(pp.getValue(x)-q.getValue(x))*(ub-ua)/(b-a)-r.getValue(x)*w(x);
    }
    /*
    function ff(x:real):real;
    begin
     ff:=f(x)+(pp(x)-q(x))*(ub-ua)/(b-a)-r(x)*w(x)
    end{ff};
    */
    private float g(int k, int i, int j, float x) throws Exception {
        if(k==0) return s(i,j,x);
        else return ff(x)*v(i,x);
    }
    /*
    function g(k,i,j:integer;x:real):real;
    begin
     if k=0 then g:=s(i,j,x)
     else g:=ff(x)*v(i,x)
    end{g};
    */

    private float u(float x){
        return uu(x)+w(x);
    }
    /*
    function u(x:real):real;
    {Rozwiazanie przyblizone}
    begin
     u:=uu(x)+w(x)
    end{u};
    */
    private float integral(int k, int i, int j, float alfa, float beta) throws Exception {
        int l;
        float   tau = (beta-alfa)/2,
                s = (beta+alfa)/2,
                t = (float)(tau*Math.sqrt(3.0)/3),
                a1 = s-t,
                a2 = s+t;
        return tau*(g(k,i,j,a1)+g(k,i,j,a2));
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

    private void trojdiag(int n, float[] a, float[] b, float[] c, float[] f, float[] x){
        float m;

        for(int k=1; k < n-1; k++){
            m=b[k]/a[k-1];
            a[k]-=m*c[k-1];
            f[k]-=m*f[k-1];
        }
        x[n-1] = f[n-1]/a[n-1];
        for(int k=n-2; k>=0; k--){
             x[k]=(f[k]-c[k]*x[k+1])/a[k];
        }
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
   /*

    procedure wykres(a,b:real;x1,y1,x2,y2:integer);
    { Procedura rysuje wykres funkcji u(x) w przedziale [a;b] }
    { w oknie (x1,y1),(x2,y2) }
    var nx,ny,i:integer;
        min,max,h,y,w:real;
    begin
     setviewport(x1,y1,x2,y2,true);
     clearviewport;
     nx:=x2-x1;
     ny:=y2-y1;
     rectangle(0,0,nx,ny);
     h:=(b-a)/nx;
     min:=u(a);
     max:=u(a);
     for i:=1 to nx do
     begin
      y:=u(a+i*h);
      if min>y then min:=y;
      if max<y then max:=y
     end;
     if max=min then
    { for i:=1 to nx-1 do putpixel(i,ny div 2,14)}
     line(1,ny div 2,nx-1,ny div 2)
     else
     begin
      w:=ny/(max-min);
      for i:=0 to nx-1 do
      line(i,ny-trunc(w*(u(a+i*h)-min)),i+1,ny-trunc(w*(u(a+(i+1)*h)-min)))
     end
    end{wykres};
      */

    /*
    BEGIN {Poczatek programu}

     repeat
    window(1,1,80,25);
    textbackground(1);
    textcolor(14);
    clrscr;
    writeln;
    writeln('       PROGRAM ROZWIAZUJE METODA ELEMENTU SKONCZONEGO ROWNANIE');
    writeln('               -(p(x)u'')''+ q(x)u''+ r(x)u = f(x)');
    writeln('       Z NIEJEDNORODNYMI WARUNKAMI BRZEGOWYMI u(a)=ua i u(b)=ub');
    writeln('           Z ZASTOSOWANIEM ELEMENTOW LINIOWCH');
    writeln;
    writeln(' Podaj przedzial,w ktorym chcesz rozwiazac rownanie:');
    czytreal(a,' a');
    czytreal(b,' b');
    writeln(' Podaj wartosci brzegowe:');
    czytreal(ua,' ua');
    czytreal(ub,' ub');
    writeln(' Podaj liczbe podzialow odcinka [a,b] (n<=1000):');
    czytint(n,' n');

    h:=(b-a)/n;

    for i:=0 to n-1 do xi[i]:=a+i*h;
    xi[n]:=b;

    {Obliczanie wspolczynnikow macierzy}
    for i:=2 to n-1 do ab[i]:=calka(0,i-1,i,xi[i-1],xi[i]);
    for i:=1 to n-1 do aa[i]:=calka(0,i,i,xi[i-1],xi[i])
                             +calka(0,i,i,xi[i],xi[i+1]);
    for i:=1 to n-2 do ac[i]:=calka(0,i+1,i,xi[i],xi[i+1]);

    {Obliczanie wektora prawych stron}
    for i:=1 to n-1 do af[i]:=calka(1,i,i,xi[i-1],xi[i])
                             +calka(1,i,i,xi[i],xi[i+1]);

    {Rozwiazanie ukladu rownan}
    trojdiag(n-1,aa,ab,ac,af,ax);
    ax[0]:=0;
    ax[n]:=0;

    clrscr;
    write('NR WEZLA      WARTOSC x       ROZWIAZANIE');
    window(2,3,79,25);
    clrscr;
    for i:=0 to n do
    begin
     writeln(i:5,'    ',xi[i]:12:6,'      ',u(a+i*h):12:6);
     if ((i+1) div 20*20=i+1) or (i=n) then ENTER
    end;

    writeln;
    writeln('CZY RYSOWAC WYKRES ROZWIAZANIA ? (T/N)');
    z:=readkey;
    if (z='t') or (z='T') then
    begin
     ig:=detect;
     initgraph(ig,jg,sc);
     setbkcolor(1);
     setcolor(14);
     setlinestyle(0,0,1{3});
     wykres(a,b,10,10,getmaxx-10,getmaxy-10);
     settextstyle(0,0,1);
     outtextxy(500,440,'Wcisnij Enter');
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
}
