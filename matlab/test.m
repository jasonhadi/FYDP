    b = [ 1 0 0; 0 1 0; 0 0 1];
    [x,y] = meshgrid(0:2, 0:2);
    [xi, yi] = meshgrid(0:0.1:2, 0:0.1:2);
    bi = interp2(x,y,b,xi,yi);
    c = [1 0 1; 1 0 1; 1 0 1];
 
    
    %hold on;
  surf(b, c);
   
   colormap([0 0 0; 1 1 1]);
    %caxis([0 1024]);
    %caxis manual;

   axis([0 3 0 3 0 5]);
   drawnow;
