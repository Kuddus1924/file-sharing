f0 = 980; % частота несущей
f1 = 1180;
Vm = 300; % модулИционнаИ скорость
Vi = 300; %информационнаИ скорость
T = 5/Vm; % период следованиИ сигнала
T0 = 1/f0; %период несущей
q = 2; % количество сигналов
E = 0.00164;
n = 32; % отсч™ты
dT = T0 / n; % интервал дискретзации
 A = sqrt((2 * E) / T);
dF = f0/n;
 
t = 0:dT:T; %длительность сигнала
s = zeros(q, length(t));    %сигналы
F = [f0,f1];
%построение сигналов
for i = 1 : q
    s(i, :) = A * cos(2*pi*F(i)*t);
end
%построение базисных функций
f_baz0 = @(t)(sqrt(2 / T) * cos(2 * pi * f0 * t));
f_baz1 = @(t)(sqrt(2 / T) * cos(2 * pi * f1 * t));
s0 = @(t) (sqrt((2 * E) / T) * cos(2 * pi * f0 * t));
s3 = @(t) (sqrt((2 * E) / T) * cos(2 * pi * f1 * t));
fi1 = @(t)f_baz0;
fi2 = @(t)f_baz1;
s1 = zeros(q,1);
s2 = zeros(q,1);
%построение сигнального созвездиИ

    s1(1) = integral(@(t) (s0(t) .* f_baz0(t)), 0, T);
    s2(1) = integral(@(t) (s0(t) .* f_baz1(t)), 0, T);
    s1(2) = integral(@(t) (s3(t) .* f_baz0(t)), 0, T);
    s2(2) = integral(@(t) (s3(t) .* f_baz1(t)), 0, T);
     
    plot(s1,s2,'o');
    hold on;
    grid on;
 
plot([-0.005,0.045],[-0.005,0.045],'b')

 
 
%??Н?ЦЉУЭЦ Чї?НЉОЭђ
 
SNRdB = 1:10; %Чигнал шум
 SNRdBinit = 6;    % начальное значение отношени€ сигнал/шум
   SNRdBfin  = 12;   % конечное значение отношени€ сигнал/шум
   SNRdBincr = 0.5;
SNRdBtheor = 1:10;
SNRtheor = 10.^(SNRdBtheor/10);
Pe_theor =  2*Qfun(sqrt(2*SNRtheor)*sin(pi/q)); 
nErrMax = 100;
for nSNR = 1:length(SNRdB)
    nErr = 0; nRun = 0;
    SNR = 10^(SNRdB(nSNR)/10);
    N0 = E/(2*SNR);
    sigma =  sqrt(N0);
     
    while nErr < nErrMax
        i = floor(q*rand)+1;
        r = sigma+randn(1,length(t));
        r1 =r*integral(@(t)( f_baz0(t)), 0, T);
        r2 =r* integral(@(t)( f_baz1(t)), 0, T);
        min = 10000;
        resi = 1;
        for ind = 1:q
            d = (r1 - s1(ind)).^2 + (r2 - s2(ind)).^2;
            if d < min
                min = d;
                resi = ind;
            end
        end
        if resi~=i
            nErr = nErr + 1;
        end
        if 20<nRun && nRun < 200
            hold on;
            plot(r1,r2,'b.','MarkerSize', 5);
        end
        nRun = nRun + 1; 
    end
    disp([SNRdB(nSNR),nErr,nErrMax,nRun]);
    Pe(nSNR) = nErr/nRun;
end
figure(2);
axis('square');
semilogy(SNRdBtheor, Pe_theor, 'black', SNRdB, Pe, 'red.-', 'MarkerSize', 20);
xlabel('SNRdB') 
ylabel('Pe')