p a c k a g e   c o m . e x a m p l e . n e w h o y o o ;  
  
 i m p o r t   j a v a . u t i l . A r r a y L i s t ;  
 i m p o r t   j a v a . u t i l . H a s h M a p ;  
  
 i m p o r t   c o m . a n d r o i d q u e r y . A Q u e r y ;  
  
 i m p o r t   m a i n . j a v a . c o m . s e f f o r d . c i r c u l a r p r o g r e s s d r a w a b l e . s a m p l e . C i r c u l a r P r o g r e s s D r a w a b l e ;  
 i m p o r t   a n d r o i d . a n i m a t i o n . A n i m a t o r ;  
 i m p o r t   a n d r o i d . a n i m a t i o n . A n i m a t o r S e t ;  
 i m p o r t   a n d r o i d . a n i m a t i o n . A r g b E v a l u a t o r ;  
 i m p o r t   a n d r o i d . a n i m a t i o n . O b j e c t A n i m a t o r ;  
 i m p o r t   a n d r o i d . a p p . A c t i v i t y ;  
 i m p o r t   a n d r o i d . o s . B u n d l e ;  
 i m p o r t   a n d r o i d . v i e w . V i e w ;  
 i m p o r t   a n d r o i d . v i e w . W i n d o w ;  
 i m p o r t   a n d r o i d . v i e w . a n i m a t i o n . A c c e l e r a t e D e c e l e r a t e I n t e r p o l a t o r ;  
 i m p o r t   a n d r o i d . v i e w . a n i m a t i o n . A n t i c i p a t e I n t e r p o l a t o r ;  
 i m p o r t   a n d r o i d . v i e w . a n i m a t i o n . C y c l e I n t e r p o l a t o r ;  
 i m p o r t   a n d r o i d . v i e w . a n i m a t i o n . O v e r s h o o t I n t e r p o l a t o r ;  
 i m p o r t   a n d r o i d . w i d g e t . A d a p t e r V i e w ;  
 i m p o r t   a n d r o i d . w i d g e t . B u t t o n ;  
 i m p o r t   a n d r o i d . w i d g e t . I m a g e B u t t o n ;  
 i m p o r t   a n d r o i d . w i d g e t . I m a g e V i e w ;  
 i m p o r t   a n d r o i d . w i d g e t . L i s t V i e w ;  
 i m p o r t   a n d r o i d . w i d g e t . S i m p l e A d a p t e r ;  
 i m p o r t   a n d r o i d . w i d g e t . T o a s t ;  
 i m p o r t   a n d r o i d . w i d g e t . A d a p t e r V i e w . O n I t e m C l i c k L i s t e n e r ;  
  
 / * *  
   *   C i r c u l a r   p r o g r e s s   d r a w a b l e   d e m o n s t r a t i o n  
   *  
   *   @ a u t h o r   S a u l   D i a z   < s e f f o r d @ g m a i l . c o m >  
   * /  
 p u b l i c   c l a s s   s i n g l e I n v i t e   e x t e n d s   A c t i v i t y   {  
 	 f l o a t   e m p t y = 0 ;  
 	 f l o a t   f u l l = 0 . 1 f ;  
 	 f l o a t   m a x ;  
 	 A Q u e r y   a q   ;  
 	 I m a g e V i e w   i m g 0 , i m g 1 , i m g 2 , i m g 3 , i m g 4 ;  
 	 I m a g e B u t t o n   i m g B 1 , i m g B 2 , i m g B 3 , i m g B 4 ;  
 	 / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /  
 	 / * *   C a l l e d   w h e n   t h e   a c t i v i t y   i s   f i r s t   c r e a t e d .   * /        
         / / �XfL i s t V i e w �[a�       
         L i s t V i e w   m y L i s t V i e w 1 ;    
         / / H a s h M a p < S t r i n g ,   O b j e c t >   p M a p _ 1 = n e w   H a s h M a p < S t r i n g , O b j e c t > ( ) ;    
         / / H a s h M a p < S t r i n g ,   O b j e c t >   p M a p 1 _ 1 = n e w   H a s h M a p < S t r i n g , O b j e c t > ( ) ;    
         / / H a s h M a p < S t r i n g ,   O b j e c t >   p M a p 2 _ 1 = n e w   H a s h M a p < S t r i n g , O b j e c t > ( ) ;    
         / / H a s h M a p < S t r i n g ,   O b j e c t >   p M a p 3 _ 1 = n e w   H a s h M a p < S t r i n g , O b j e c t > ( ) ;  
         / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * / 	  
         H a s h M a p [ ]   p M a p l i s t   =   n e w   H a s h M a p [ 4 ] ;    
          
         / /   V i e w s  
         I m a g e V i e w   i v D r a w a b l e ;  
         B u t t o n   b t S t y l e 2 ;  
  
         C i r c u l a r P r o g r e s s D r a w a b l e   d r a w a b l e ;  
         V i e w . O n C l i c k L i s t e n e r   l i s t e n e r   =   n e w   V i e w . O n C l i c k L i s t e n e r ( )   {  
                 @ O v e r r i d e  
                 p u b l i c   v o i d   o n C l i c k ( V i e w   v )   {  
                         i f   ( c u r r e n t A n i m a t i o n   ! =   n u l l )   {  
                                 c u r r e n t A n i m a t i o n . c a n c e l ( ) ;  
                         }  
                         c u r r e n t A n i m a t i o n   =   p r e p a r e S t y l e 2 A n i m a t i o n ( ) ;  
                         c u r r e n t A n i m a t i o n . s t a r t ( ) ;  
                          
                         e m p t y + = 0 . 1 f ;  
                         f u l l = e m p t y + 0 . 1 f ;  
                 }  
         } ;  
  
         A n i m a t o r   c u r r e n t A n i m a t i o n ;  
  
         @ O v e r r i d e  
         p u b l i c   v o i d   o n C r e a t e ( B u n d l e   s a v e d I n s t a n c e S t a t e )   {  
                 s u p e r . o n C r e a t e ( s a v e d I n s t a n c e S t a t e ) ;  
                 a q   =   n e w   A Q u e r y ( t h i s ) ;  
                 r e q u e s t W i n d o w F e a t u r e ( W i n d o w . F E A T U R E _ N O _ T I T L E ) ;  
                 s e t C o n t e n t V i e w ( R . l a y o u t . s i n g l e _ i n v i t e ) ;  
                  
                 / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /  
                 f o r ( i n t   i = 0 ; i < 4 ; i + + )  
                 {  
                 	 p M a p l i s t [ i ] = n e w   H a s h M a p < S t r i n g , O b j e c t > ( ) ;  
                 }  
                 / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /  
                 / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /  
 	         m y L i s t V i e w 1 = ( L i s t V i e w ) f i n d V i e w B y I d ( R . i d . l i s t V i e w 1 ) ;  
 	         A r r a y L i s t < H a s h M a p < S t r i n g , O b j e c t > >   p r o g r a m e L i s t = n e w   A r r a y L i s t < H a s h M a p < S t r i n g , O b j e c t > > ( ) ;      
 	         f o r ( i n t   i = 0 ; i < 4 ; i + + )    
 	         {    
 	         	 p M a p l i s t [ i ] . p u t ( " p i c t u r e " , R . d r a w a b l e . d 0 5 ) ;    
 	         	 p M a p l i s t [ i ] . p u t ( " n a m e " ,   " +������������" ) ;    
 	         	 p M a p l i s t [ i ] . p u t ( " c o n t e n t " ,   " � �����" ) ;  
 	         	 p M a p l i s t [ i ] . p u t ( " t i m e " ,   " �����������������" ) ;  
                 	 p r o g r a m e L i s t . a d d ( p M a p l i s t [ i ] ) ;  
 	         	 / *  
 	                 	 s w i t c h ( i ) {  
 	                 	 c a s e   0 :  
 	                 	 	  
 	                         	 p M a p _ 1 . p u t ( " p i c t u r e " , R . d r a w a b l e . d 0 5 ) ;    
 	                         	 p M a p _ 1 . p u t ( " n a m e " ,   " +������������" ) ;    
 	                         	 p M a p _ 1 . p u t ( " c o n t e n t " ,   " � �����" ) ;  
 	                         	 p M a p _ 1 . p u t ( " t i m e " ,   " �����������������" ) ;  
 	                         	 p r o g r a m e L i s t . a d d ( p M a p _ 1 ) ;  
 	                         	 b r e a k ;  
 	                 	 c a s e   1 :  
 	                 	 	  
 	                         	 p M a p 1 _ 1 . p u t ( " p i c t u r e " , R . d r a w a b l e . d 0 6 ) ;    
 	                         	 p M a p 1 _ 1 . p u t ( " n a m e " ,   " ���9������" ) ;      
 	                         	 p M a p 1 _ 1 . p u t ( " c o n t e n t " ,   " � �����" ) ;  
 	                         	 p M a p 1 _ 1 . p u t ( " t i m e " ,   " �����������������" ) ;  
 	                         	 p r o g r a m e L i s t . a d d ( p M a p 1 _ 1 ) ;  
 	                         	 b r e a k ;  
 	                 	 c a s e   2 :  
 	                 	 	  
 	                         	 p M a p 2 _ 1 . p u t ( " p i c t u r e " , R . d r a w a b l e . d 0 7 ) ;    
 	                         	 p M a p 2 _ 1 . p u t ( " n a m e " ,   " ���3~��" ) ;      
 	                         	 p M a p 2 _ 1 . p u t ( " c o n t e n t " ,   " � �����" ) ;  
 	                         	 p M a p 2 _ 1 . p u t ( " t i m e " ,   " �����������������" ) ;  
 	                         	 p r o g r a m e L i s t . a d d ( p M a p 2 _ 1 ) ;  
 	                         	 b r e a k ;  
 	                 	 c a s e   3 :  
 	                 	 	  
 	                         	 p M a p 3 _ 1 . p u t ( " p i c t u r e " , R . d r a w a b l e . d 0 8 ) ;    
 	                         	 p M a p 3 _ 1 . p u t ( " n a m e " ,   " ����������" ) ;      
 	                         	 p M a p 3 _ 1 . p u t ( " c o n t e n t " ,   " � �����" ) ;  
 	                         	 p M a p 3 _ 1 . p u t ( " t i m e " ,   " �����������������" ) ;  
 	                         	 p r o g r a m e L i s t . a d d ( p M a p 3 _ 1 ) ;  
 	                         	 b r e a k ;  
 	                         }  
 	         	 * /  
 	                 }    
 	                        
 	                 / / ������S i m p l e A d a p t e r ��������������������       
 	                 f i n a l   S i m p l e A d a p t e r   m y S i m p l e A d a p t e r = n e w   S i m p l e A d a p t e r ( t h i s ,        
 	                 	 	 p r o g r a m e L i s t ,  
 	                 	 	 / / m y A r r a y L i s t , / / ������4       
 	                                 R . l a y o u t . i t e m _ s i n g l e i n v i t e , / / L i s t V i e w �����������y��������2��������<��l i s t i t e m . x m l        
 	                                 n e w   S t r i n g [ ] { " p i c t u r e " , " n a m e " , " t i m e " , " c o n t e n t "   } , / / H a s h M a p ��5����������k e y �  i t e m T i t l e ����i t e m C o n t e n t       , " i t e m C o n t e n t "  
 	                                 n e w   i n t [ ] { R . i d . i m a g e v i e w _ i t e m , R . i d . t e x t v i e w _ i t e m , R . i d . c o n t e n t t e x t , R . i d . r e l e a s e t i m e   } ) ;  
 	                 / * ����������<��l i s t i t e m . x m l ��������������i d         , R . i d . i t e m C o n t e n t ����������<����8����������������}MH a s h M a p ��8��*�����������������������* /        
 	                        
 	                 m y L i s t V i e w 1 . s e t A d a p t e r ( m y S i m p l e A d a p t e r ) ;        
 	                 / / ������������ ��       
 	                 m y L i s t V i e w 1 . s e t O n I t e m C l i c k L i s t e n e r ( n e w   O n I t e m C l i c k L i s t e n e r ( ) {        
 	                         @ O v e r r i d e        
 	                         p u b l i c   v o i d   o n I t e m C l i c k ( A d a p t e r V i e w < ? >   a r g 0 ,   V i e w   a r g 1 ,   i n t   a r g 2 ,        
 	                                         l o n g   a r g 3 )   {        
 	                         	 / / ������a����������H a s h M a p ��������       
 	                                 H a s h M a p < S t r i n g , S t r i n g >   m a p = ( H a s h M a p < S t r i n g , S t r i n g > ) m y L i s t V i e w 1 . g e t I t e m A t P o s i t i o n ( a r g 2 ) ;        
 	                                 S t r i n g   t i t l e = m a p . g e t ( " i t e m T i t l e " ) ;        
 	                                 S t r i n g   c o n t e n t = m a p . g e t ( " i t e m C o n t e n t " ) ;      
 	                                  
 	                                 f o r ( i n t   i = 0 ; i < 4 ; i + + )  
 	                                 {  
 	                                 	 i f ( a r g 2 = = i )  
 	                                 	 {  
 	                                 	 	 T o a s t . m a k e T e x t ( g e t A p p l i c a t i o n C o n t e x t ( ) ,          
 	 	 	                                                 " ����a���������" + a r g 2 + " ����I t e m ����i t e m C o n t e n t ���������: " + c o n t e n t ,        
 	 	 	                                                 T o a s t . L E N G T H _ S H O R T ) . s h o w ( ) ; 	        
 	                                 	 }  
 	                                 }  
 	                                 / *  
 	                         	 s w i t c h ( a r g 2 ) {  
 	 	                 	 c a s e   0 :  
 	 	                 	 	 T o a s t . m a k e T e x t ( g e t A p p l i c a t i o n C o n t e x t ( ) ,          
 	 	                                                 " ����a���������" + a r g 2 + " ����I t e m ����i t e m C o n t e n t ���������: " + c o n t e n t ,        
 	 	                                                 T o a s t . L E N G T H _ S H O R T ) . s h o w ( ) ; 	                 	 	  
 	 	                         	 b r e a k ;  
 	 	                 	 c a s e   1 :  
 	 	                 	 	 T o a s t . m a k e T e x t ( g e t A p p l i c a t i o n C o n t e x t ( ) ,          
 	 	                                                 " ����a���������" + a r g 2 + " ����I t e m ����i t e m C o n t e n t ���������: " + c o n t e n t ,        
 	 	                                                 T o a s t . L E N G T H _ S H O R T ) . s h o w ( ) ;  
 	 	                         	 b r e a k ;  
 	 	                 	 c a s e   2 :  
 	 	                 	 	 T o a s t . m a k e T e x t ( g e t A p p l i c a t i o n C o n t e x t ( ) ,          
 	 	                                                 " ����a���������" + a r g 2 + " ����I t e m ����i t e m C o n t e n t ���������: " + c o n t e n t ,        
 	 	                                                 T o a s t . L E N G T H _ S H O R T ) . s h o w ( ) ;    
 	 	                         	 b r e a k ;  
 	 	                 	 c a s e   3 :  
 	 	                 	 	 T o a s t . m a k e T e x t ( g e t A p p l i c a t i o n C o n t e x t ( ) ,          
 	 	                                                 " ����a���������" + a r g 2 + " ����I t e m ����i t e m C o n t e n t ���������: " + c o n t e n t ,        
 	 	                                                 T o a s t . L E N G T H _ S H O R T ) . s h o w ( ) ;    
 	 	                         	 b r e a k ;  
 	 	                 	 }  
 	 	                 	 * /  
 	                                        
 	                         }        
 	                                
 	                 } ) ;        
 	         / * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * /  
                 i m g 0 = ( I m a g e V i e w ) f i n d V i e w B y I d ( R . i d . i m a g e V i e w 2 ) ;  
                 i m g 1 = ( I m a g e V i e w ) f i n d V i e w B y I d ( R . i d . i m a g e V i e w 3 ) ;  
                 i m g 2 = ( I m a g e V i e w ) f i n d V i e w B y I d ( R . i d . i m a g e V i e w 4 ) ;  
                 i m g 3 = ( I m a g e V i e w ) f i n d V i e w B y I d ( R . i d . i m a g e V i e w 5 ) ;  
                 i m g 4 = ( I m a g e V i e w ) f i n d V i e w B y I d ( R . i d . i m a g e V i e w 6 ) ;  
                 i m g B 1 = ( I m a g e B u t t o n ) f i n d V i e w B y I d ( R . i d . i m a g e B u t t o n 1 ) ;  
                 i m g B 2 = ( I m a g e B u t t o n ) f i n d V i e w B y I d ( R . i d . i m a g e B u t t o n 2 ) ;  
                 i m g B 3 = ( I m a g e B u t t o n ) f i n d V i e w B y I d ( R . i d . i m a g e B u t t o n 3 ) ;  
                 i m g B 4 = ( I m a g e B u t t o n ) f i n d V i e w B y I d ( R . i d . i m a g e B u t t o n 4 ) ;  
                 / / i n i t ( ) ;  
                 i m g 1 . s e t V i s i b i l i t y ( V i e w . V I S I B L E ) ;  
         	 i m g 2 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 3 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 4 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 t h i s . a q . i d ( R . i d . i m a g e B u t t o n 1 ) . c l i c k e d ( t h i s ,   " f i r s t I m a g e " ) ;  
         	 t h i s . a q . i d ( R . i d . i m a g e B u t t o n 2 ) . c l i c k e d ( t h i s ,   " s e c o n d I m a g e " ) ;  
         	 t h i s . a q . i d ( R . i d . i m a g e B u t t o n 3 ) . c l i c k e d ( t h i s ,   " t h r e e I m a g e " ) ;  
         	 t h i s . a q . i d ( R . i d . i m a g e B u t t o n 4 ) . c l i c k e d ( t h i s ,   " f o u r I m a g e " ) ;  
  
         	 t h i s . a q . i d ( R . i d . b u t t o n 1 ) . b a c k g r o u n d ( R . d r a w a b l e . b u t t o n e f f e c t _ r e d ) ;  
         	 t h i s . a q . i d ( R . i d . b u t t o n 2 ) . b a c k g r o u n d ( R . d r a w a b l e . b u t t o n e f f e c t _ r e d ) ;  
         	 t h i s . a q . i d ( R . i d . b u t t o n 3 ) . b a c k g r o u n d ( R . d r a w a b l e . b u t t o n e f f e c t _ r e d ) ;  
         	 t h i s . a q . i d ( R . i d . b u t t o n 1 ) . c l i c k e d ( t h i s ,   " r e s e n d " ) ;  
         	 t h i s . a q . i d ( R . i d . b u t t o n 2 ) . c l i c k e d ( t h i s ,   " r e s p o n d " ) ;  
         	 t h i s . a q . i d ( R . i d . b u t t o n 3 ) . c l i c k e d ( t h i s ,   " g o o d " ) ;  
                  
                 i v D r a w a b l e   =   ( I m a g e V i e w )   f i n d V i e w B y I d ( R . i d . i v _ d r a w a b l e ) ;  
                 b t S t y l e 2   =   ( B u t t o n )   f i n d V i e w B y I d ( R . i d . b t _ s t y l e _ 2 ) ;  
  
                 d r a w a b l e   =   n e w   C i r c u l a r P r o g r e s s D r a w a b l e ( g e t R e s o u r c e s ( ) . g e t D i m e n s i o n P i x e l S i z e ( R . d i m e n . d r a w a b l e _ r i n g _ s i z e ) ,  
                                 g e t R e s o u r c e s ( ) . g e t C o l o r ( a n d r o i d . R . c o l o r . d a r k e r _ g r a y ) ,  
                                 g e t R e s o u r c e s ( ) . g e t C o l o r ( a n d r o i d . R . c o l o r . h o l o _ g r e e n _ l i g h t ) ,  
                                 g e t R e s o u r c e s ( ) . g e t C o l o r ( a n d r o i d . R . c o l o r . h o l o _ r e d _ d a r k ) ) ;  
                 i v D r a w a b l e . s e t I m a g e D r a w a b l e ( d r a w a b l e ) ;  
                 b t S t y l e 2 . s e t O n C l i c k L i s t e n e r ( l i s t e n e r ) ;  
         }  
         / * *  
           *   S t y l e   2   a n i m a t i o n   w i l l   f i l l   t h e   o u t e r   r i n g   w h i l e   a p p l y i n g   a   c o l o r   e f f e c t   f r o m   r e d   t o   g r e e n  
           *  
           *   @ r e t u r n   A n i m a t i o n  
           * /  
         p r i v a t e   A n i m a t o r   p r e p a r e S t y l e 2 A n i m a t i o n ( )   {  
                 A n i m a t o r S e t   a n i m a t i o n   =   n e w   A n i m a t o r S e t ( ) ;  
  
                 O b j e c t A n i m a t o r   p r o g r e s s A n i m a t i o n   =   O b j e c t A n i m a t o r . o f F l o a t ( d r a w a b l e ,   C i r c u l a r P r o g r e s s D r a w a b l e . P R O G R E S S _ P R O P E R T Y ,   e m p t y ,   f u l l ) ; / / 0 f 1 f  
                 p r o g r e s s A n i m a t i o n . s e t D u r a t i o n ( 1 8 0 0 ) ;  
                 p r o g r e s s A n i m a t i o n . s e t I n t e r p o l a t o r ( n e w   A c c e l e r a t e D e c e l e r a t e I n t e r p o l a t o r ( ) ) ;  
                  
                 A n i m a t o r   i n n e r C i r c l e A n i m a t i o n   =   O b j e c t A n i m a t o r . o f F l o a t ( d r a w a b l e ,   C i r c u l a r P r o g r e s s D r a w a b l e . C I R C L E _ F I L L _ P R O P E R T Y ,   e m p t y ,   f u l l ) ; / / 0 f 1 . 2 f  
                 i n n e r C i r c l e A n i m a t i o n . s e t D u r a t i o n ( 1 8 0 0 ) ;  
                 i n n e r C i r c l e A n i m a t i o n . s e t I n t e r p o l a t o r ( n e w   A c c e l e r a t e D e c e l e r a t e I n t e r p o l a t o r ( ) ) ;  
  
                 O b j e c t A n i m a t o r   c o l o r A n i m a t o r   =   O b j e c t A n i m a t o r . o f I n t ( d r a w a b l e ,   " r i n g C o l o r " ,   g e t R e s o u r c e s ( ) . g e t C o l o r ( a n d r o i d . R . c o l o r . h o l o _ g r e e n _ l i g h t ) ,  
                                 g e t R e s o u r c e s ( ) . g e t C o l o r ( a n d r o i d . R . c o l o r . h o l o _ r e d _ d a r k ) ) ;  
                 c o l o r A n i m a t o r . s e t E v a l u a t o r ( n e w   A r g b E v a l u a t o r ( ) ) ;  
                 c o l o r A n i m a t o r . s e t D u r a t i o n ( 1 8 0 0 ) ;  
  
                 a n i m a t i o n . p l a y T o g e t h e r ( p r o g r e s s A n i m a t i o n ,   c o l o r A n i m a t o r ,   i n n e r C i r c l e A n i m a t i o n ) ;  
                 r e t u r n   a n i m a t i o n ;  
         }  
  
         p u b l i c   v o i d   f i r s t I m a g e ( )  
         {  
         	 i m g 0 . s e t I m a g e D r a w a b l e ( i m g B 1 . g e t D r a w a b l e ( ) ) ;  
         	 i m g 1 . s e t V i s i b i l i t y ( V i e w . V I S I B L E ) ;  
         	 i m g 2 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 3 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 4 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         }  
         p u b l i c   v o i d   s e c o n d I m a g e ( )  
         {  
         	 i m g 0 . s e t I m a g e D r a w a b l e ( i m g B 2 . g e t D r a w a b l e ( ) ) ;  
         	 i m g 1 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 2 . s e t V i s i b i l i t y ( V i e w . V I S I B L E ) ;  
         	 i m g 3 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 4 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         }  
         p u b l i c   v o i d   t h r e e I m a g e ( )  
         {  
         	 i m g 0 . s e t I m a g e D r a w a b l e ( i m g B 3 . g e t D r a w a b l e ( ) ) ;  
         	 i m g 1 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 2 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 3 . s e t V i s i b i l i t y ( V i e w . V I S I B L E ) ;  
         	 i m g 4 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         }  
         p u b l i c   v o i d   f o u r I m a g e ( )  
         {  
         	 i m g 0 . s e t I m a g e D r a w a b l e ( i m g B 4 . g e t D r a w a b l e ( ) ) ;  
         	 i m g 1 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 2 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 3 . s e t V i s i b i l i t y ( V i e w . G O N E ) ;  
         	 i m g 4 . s e t V i s i b i l i t y ( V i e w . V I S I B L E ) ;  
         }  
          
         p u b l i c   v o i d   i n i t ( )  
         {  
         	 i m g 1 . s e t I m a g e R e s o u r c e ( 0 ) ;  
         	 i m g 2 . s e t I m a g e R e s o u r c e ( 0 ) ;  
         	 i m g 3 . s e t I m a g e R e s o u r c e ( 0 ) ;  
         	 i m g 4 . s e t I m a g e R e s o u r c e ( 0 ) ;  
         }  
         p u b l i c   v o i d   r e s e n d ( )  
         {  
         	  
         }  
         p u b l i c   v o i d   r e s p o n d ( )  
         {  
         	  
         }  
         p u b l i c   v o i d   g o o d ( )  
         {  
         	  
         }  
 }  
  
 