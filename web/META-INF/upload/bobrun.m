clc
close all

f0 = 900;
f1 = 1200;
Vmod = 300;
Vinf = 300;
T = 1 / Vmod;
dt = T / 100;
t = 0 : dt : T;
q = 2^(Vinf * (1/Vmod));
E = 5;

f = 0:10:3*f0;

s0 = @(t) (sqrt((2 * E) / T) * cos(2 * pi * f0 * t));
s1 = @(t) (sqrt((2 * E) / T) * cos(2 * pi * f1 * t));

f_baz0 = @(t)(sqrt(2 / T) * cos(2 * pi * f0 * t));
f_baz1 = @(t)(sqrt(2 / T) * cos(2 * pi * f1 * t));


%hold on
figure(1)
plot(t, f_baz0(t));
figure(2)
plot(t, f_baz1(t));
%grid on;

basMult = @(t) f_baz0(t) .* f_baz1(t);
skalar = integral(basMult, 0, T);
disp(skalar);
%fprintf("%f \n \n",skalar);
basMult = @(t) f_baz0(t) .* f_baz0(t);
skalar = integral(basMult, 0, T);
disp(skalar);
%fprintf("%f \n \n",skalar);
basMult = @(t) f_baz1(t) .* f_baz1(t);
skalar = integral(basMult, 0, T);
disp(skalar);
%fprintf("%f \n \n",skalar);

S = zeros(q, q);

S(1,1) = integral(@(t) (s0(t) .* f_baz0(t)), 0, T);
S(1,2) = integral(@(t) (s0(t) .* f_baz1(t)), 0, T);
S(2,1) = integral(@(t) (s1(t) .* f_baz0(t)), 0, T);
S(2,2) = integral(@(t) (s1(t) .* f_baz1(t)), 0, T);

disp(S);

x = [2.5 0];
y = [0.5 0];

figure(3)

plot(S(1,1),S(1,2),'-ob');
hold on
plot(S(2,1), S(2,2),'-ob');
line(x,x);
line(-y,-y);

% axis([-0.5*sqrt(E), 2*sqrt(E), -0.5*sqrt(E), 2*sqrt(E)])
% hold on;
% plot(S(1,1), S(1,2), 'ko');
% plot(S(2,1), S(2,2), 'ko');
% 
% line(y, x);
% line(x, y);
% line(y, -x);
% line(-x, y);
% line(x,x);
% line(-x,-x);
%diagonal