f0 = 980; %частота 
f1 = 1180; %частот
T0 = 1/f0; %период несущей частоты
T1 = 1/f1;
Vmod = 300;
Vi = 300;
T = 1 / Vmod;
A = sqrt(2 / T);
q = 2^(round(T*Vi));
samples = 50;
dF = f0 / samples;
dF1 = f1 / samples;
F1 = 0 : dF : (dF * 100);
F2 = 0 : dF1 : (dF1 * 100);
s = zeros(1,length(F1));
s = A.*abs(sinc((f0-F1).*T).*sinc((f0+F1).*T));
f = zeros(1,length(F2));
f = A.*abs(sinc((f1-F2).*T).*sinc((f1+F2).*T));
figure(1);
plot(F1, s(1,:))
title('График амплитудного спектра')
plot(F2, f(1,:))
xlabel('f');
ylabel('S(f)');

% ПОСЛЕДОВАТЕЛЬНОСТЬ %
N =5*2;
S = zeros(2,length(s));
S(1,:)= s;
S(2,:)= f;

multI = round((q-1)*rand(1, N));
So = zeros(2,length(F1)); 
So(1,:)= F1;
So(2,:)= F2;


Si = zeros(1, length(F1));
for k = 1:N
    Si = Si + S(multI(k) + 1, :).*exp(-(1j*2*pi*(k-1)*T)*So(multI(k) + 1, :));
end
figure(3);
plot(F1, abs(Si));
title(['N = 6']);
xlabel('f');
ylabel('S(f)');

