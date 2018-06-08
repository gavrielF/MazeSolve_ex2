clear all

%% use 3 least-square criteria to fit a line to data points
figure(1);
clf;
set(1,'Position',[287    35   732   656]);

% the data points
x = [-0.008868346;-0.04820709;-0.1161587;-0.1958451;-0.273318;-0.3431231;
    -0.4157971;-0.4825487;-0.54146;-0.6003713;-0.6592826;-0.7166834;
    -0.7754878;-0.8334865;-0.8959592;-0.9592876;-1.031962;-1.101725;
    -1.177268;-1.259683;-1.347606;-1.440629;-1.538344;-1.643257;
    -1.753272;-1.872179;-1.9943;-2.129534;-2.264348;-2.397455];
y = [0.4888085; 1.276716;2.107274; 2.903296; 3.677206; 4.485307;5.326619;
    6.190711;7.055374;7.920035;8.784698;9.62719;10.43617;11.23406;12.04276;
    12.86254;13.70385;14.55656;15.43108;16.29382;17.13368;17.95062;18.74463;19.53772;
    20.34134;21.15492;21.99048;22.83528;23.70265;24.55903];

%% A. plot the data
subplot(2,2,1);
plot(x,y,'or');
title('y = a*x + b first edge');
axis square

% subplot(2,2,2);
% plot(x,y,'or');
% title('y = a*x + b second edge');
% axis square
% 
% subplot(2,2,3);
% plot(x,y,'or');
% title('a*x + b*y + c = 0');
% axis square
% 
% subplot(2,2,4);
% plot(x,y,'or');
% title('all');
% axis square

%% B. Estimatation useing least squares

%% B1. y = ax + b
% (a) use least square estimation of a,b in    y = ax+b
[a,b] = func_line_fit(x,y)

% (b) use least square estimation of a,b in    y = ax+b
% but with a variant that first moves the points to zero mean
% then finds a, and then calcs b
[a1,b1] = func_line_fit1(x,y)
% note that the results are the same

% plot the estimated line
% subplot(2,2,4);
% hold on;
% plot(x,a1*x+b1,'r');
% plot(x,a*x+b,'-om');

subplot(2,2,1);
hold on;
plot(x,a1*x+b1,'r');
%plot(x,a*x+b,'-om');

% pause

%Ey = 0; Dy = 0;
% show the distances of the points from the line
%for i=1:length(x)
%  plot([x(i) x(i)],[y(i),a*x(i)+b],':r');
%  Ey = Ey + abs((y(i) -  (a*x(i)+b)));
%  Dy = Dy + (y(i) -  (a*x(i)+b));
%end
%xlabel(['sum abs errors = ' num2str(Ey) '   sum errors  = ' num2str(Dy)]);

%second edge
%x = [1.0;    0.5;   1.0;   1.2;   1.0;   1.5];
%y = [6.0; 5.5;   4.5; 3.5; 3.0; 1.5];


%[a,b] = func_line_fit(x,y)
%[a1,b1] = func_line_fit1(x,y)

%subplot(2,2,1);
%hold on;
%plot(x,a1*x+b1,'r');
%plot(x,a*x+b,'-om');

%Ey = 0; Dy = 0;
% show the distances of the points from the line
%for i=1:length(x)
%  plot([x(i) x(i)],[y(i),a*x(i)+b],':r');
%  Ey = Ey + abs((y(i) -  (a*x(i)+b)));
%  Dy = Dy + (y(i) -  (a*x(i)+b));
%end
%xlabel(['sum abs errors = ' num2str(Ey) '   sum errors  = ' num2str(Dy)]);


% %% B2. use least square estimation of m,n in    x = my+n
% [m,n] = func_line_fit(y,x);
% 
% % plot the estimated line
% subplot(2,2,4);
% hold on;
% plot(m*y+n,y,'g'); 
% 
% subplot(2,2,2);
% hold on;
% plot(m*y+n,y,'-g.'); 
% 
% Ex = 0; Dx = 0;
% % show the distances of the points from the line
% for i=1:length(x)
%   plot([x(i) m*y(i)+n],[y(i),y(i)],':r');
%   Ex = Ex + abs(x(i) -  (m*y(i)+n));
%   Dx = Dx + (x(i) -  (m*y(i)+n));
% end
% xlabel(['sum abs errors = ' num2str(Ex) '   sum errors = ' num2str(Dx)]);
% 
% %% B3. uses least square estimation of a,b,c in a*x+b*y+c=0
% % (a) the correct way:
% %    move the points mean to zero by subtructing the mean from all points; 
% %    estimate (a,b); 
% %    use the original mean and the estimated (a,b) to calc c
% [a,b,c] = param_line_fit(x,y);
% % (b) and the incorrect way:
% %    estimating a1,b1,c1 at once
% [a1,b1,c1] = param_line_fit1(x,y);
% 
% 
% % plot the estimated line using      x = (-b/a)*y -(c/a);
% % if a==0 we can use y = -(a/b)*x -(c/b); 
% % this is not the case for this example
% 
% subplot(2,2,3);
% hold on
% plot(-(b/a)*y -(c/a),y,'k');
% plot(-(b1/a1)*y -(c1/a1),y,'b');
% 
% % plot the line from the incorrect way
% subplot(2,2,4);
% plot(-(b/a)*y -(c/a),y,'k');
% plot(-(b1/a1)*y -(c1/a1),y,'b');
% 
% % show the distances of the points from the line
% % using the nearest points on the line
% subplot(2,2,3);
% Exy = 0; Dxy = 0;
% for i=1:length(x)
%   [xx,yy] = nearest_point_on_line(x(i),y(i),a,b,c);
%   plot(xx,yy,'.k');
%   plot([x(i) xx] , [y(i) yy],':r'); % connect
%   
%   d = sqrt( (x(i)-xx)^2 + (y(i)-yy)^2) % is the same size as:
%   d = [a,b,c]*[x(i);y(i);1] / norm([a,b]) % but this is signed
%   pause(0.5)
%   
%   Exy = Exy + abs(d);
%   Dxy = Dxy + d;
% end
% 
% xlabel(['sum abs errors = ' num2str(Exy) '   sum errors = ' num2str(Dxy)]);


