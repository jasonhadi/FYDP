delete(instrfindall)
clear all;
close all;
s = serial('COM8'); %assigns the object s to serial port
 
set(s, 'InputBufferSize', 1000000); %number of bytes in inout buffer
 %set(s, 'FlowControl', 'hardware');
 set(s, 'BaudRate', 9600);
% set(s, 'Parity', 'none');
 set(s, 'DataBits', 8);
 set(s, 'StopBit', 1);
set(s, 'Timeout',10);
s.ReadAsyncMode = 'continuous';
%clc;
 
disp(get(s,'Name'));
prop(1)=(get(s,'BaudRate'));
prop(2)=(get(s,'DataBits'));
prop(3)=(get(s, 'StopBit'));
prop(4)=(get(s, 'InputBufferSize'));
 
disp(['Port Setup Done!!',num2str(prop)]);
fopen(s);           %opens the serial port
t=0;
disp('Running');
x=0;
ival = 1;
sync = 1;
rows = 30;
cols = 30;
J = Synth();
Ja = AnalogSynth();
J.start();
Ja.start();

T = Touchpad();
T.start();

U = HelloWorldSwing();
U.main('');

%axisset = 0;


color = [ 0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
0	0	0	0	0	2	2	2	2	2	4	4	4	4	4	5	5	5	5	5	7	7	7	7	7	9	9	9	9	9;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;
14	14	14	14	14	16	16	16	16	16	17	17	17	17	17	19	19	19	19	19	21	21	21	21	21	23	23	23	23	23;];


while(true)
while(t<rows)  %Runs for 200 cycles - if you cant see the symbol, it is "less than" sign. so while (t less than 200)

   [a,count] =fscanf(s, '%g %g %g %g %g %g %g %g %g %g ', 1000000); %reads the data from the serial port and stores it to the matrix a
  
   if (min(a) ~= -1)

   b(t + 1,:) = a'; % = a;%(mod(t,rows) + 1, floor(t/cols) + 1) = a;
   else
       sync = 0;
       t = 99999;
   end
   t=t+1;
%    if (a == -1) 
%        t = 101;
%    end
   a=0;  %Clear the buffer
   
end
t = 0;
% while(t<cols)  %Runs for 200 cycles - if you cant see the symbol, it is "less than" sign. so while (t less than 200)
% 
%    [a,count] =fscanf(s, '%g %g %g %g %g %g %g %g %g %g ', 5000); %reads the data from the serial port and stores it to the matrix a
%   
%    if (min(a) ~= -1)
% 
%    c(t + 1,:) = a; % = a;%(mod(t,rows) + 1, floor(t/cols) + 1) = a;
%    else
%        sync = 0;
%        t = 99999;
%    end
%    t=t+1;
% %    if (a == -1) 
% %        t = 101;
% %    end
%    a=0;  %Clear the buffer
%    
% end

status = U.getPanelStatus();
    if(sync ~= 0)
%disp([num2str(t),'th iteration max= ',num2str(a)]);
    
    b = b';
%     c = c';
%     c = max(c, 0);
    %b = b - 200;
    b = max(b, 0);
    d = b;
    %d = wfusmat(b,c,'mean');
    [x,y] = meshgrid(0:cols-1, 0:rows-1);
    [xi, yi] = meshgrid(0:ival:cols-1, 0:ival:rows-1);
    bi = interp2(x,y,d,xi,yi,'cubic');

    cintp = interp2(x,y,color,xi,yi,'nearest');



     if (status(4) == 1)
         ival = 0.1;
     else
         ival = 1;
     end


    %hold on;
    %1 = heatmap en
    %2 = tp enable
    %3 = music enable
    %4 = interp enab
    %5 = heat color en
    %6 = tp mode radio
    %7 = music mode
    %8 = click thresh
    %9 = play thresh
    %10 = inst
   %surf(bi, color);
   %surf(b,b);
   % caxis([0 23]);
   % caxis manual;
    %if (axisset == 0) 
   %axisset = 1;
   % end
   if (status(1) == 1) 
     if (status(5) == 1)
         surf(bi, cintp);
         caxis([0 23]);
         caxis manual;
         axis([1 cols/ival 1 rows/ival 0 1000]);
         drawnow;
     axis([1 cols/ival 1 rows/ival 0 1000]);
     else
         surf(bi, bi);
         caxis([0 1000]);
         caxis manual;
         axis([1 cols/ival 1 rows/ival 0 1000]);
         drawnow;
     axis([1 cols/ival 1 rows/ival 0 1000]);
     end 
   end
     axis([1 cols/ival 1 rows/ival 0 1000]);
   if (status(2) == 1)
       T.updateData(bi, status(8));
   end
   if (status(3) == 1)
      if(status(7) == 0)
       J.updateData(b, status(10), status(9));
   else
    Ja.updateData(b, status(9));
end
   end
    end
    sync = 1;
t =0;    
end
 
fclose(s); %close the serial port
