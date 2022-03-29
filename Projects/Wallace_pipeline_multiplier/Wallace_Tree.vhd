----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/08/2016 06:22:46 PM
-- Design Name: 
-- Module Name: Wallace_Tree - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Wallace_Tree is
    Generic ( n : natural := 32);
    Port ( 
            clk : STD_LOGIC;
            rst : STD_LOGIC;
            X : in STD_LOGIC_VECTOR (31 downto 0);
            Y : in STD_LOGIC_VECTOR (31 downto 0);
            P : out STD_LOGIC_VECTOR (63 downto 0)
           
            );
end Wallace_Tree;

architecture Behavioral of Wallace_Tree is

type array_matrix is array (0 to n-1) of std_logic_vector(2*n-1 downto 0);
signal partial_products : array_matrix;
signal firstLevelRegisterOutputs : array_matrix;

-- 22 outputs from first level of SST (including 2 outputs that did not have a sst)
type array_matrix2 is array (0 to 21) of std_logic_vector(2*n-1 downto 0);
signal secondLevelRegisterInputs : array_matrix2;
signal secondLevelRegisterOutputs: array_matrix2;

-- 15 outputs from second level of Registers
type array_matrix3 is array (0 to 14) of std_logic_vector(2*n-1 downto 0);
signal thirdLevelRegisterInputs : array_matrix3;
signal thirdLevelRegisterOutputs: array_matrix3;


-- 10 outputs from third level of Registers
type array_matrix4 is array (0 to 9) of std_logic_vector(2*n-1 downto 0);
signal fourthLevelRegisterInputs : array_matrix4;
signal fourthLevelRegisterOutputs: array_matrix4;

-- 7 outputs from third level of Registers
type array_matrix5 is array (0 to 6) of std_logic_vector(2*n-1 downto 0);
signal fifthLevelRegisterInputs : array_matrix5;
signal fifthLevelRegisterOutputs: array_matrix5;

-- 5 outputs from third level of Registers
type array_matrix6 is array (0 to 4) of std_logic_vector(2*n-1 downto 0);
signal sixthLevelRegisterInputs : array_matrix6;
signal sixthLevelRegisterOutputs: array_matrix6;

-- 4 outputs from third level of Registers
type array_matrix7 is array (0 to 3) of std_logic_vector(2*n-1 downto 0);
signal seventhLevelRegisterInputs : array_matrix7;
signal seventhLevelRegisterOutputs: array_matrix7;

-- 3 outputs from third level of Registers
type array_matrix8 is array (0 to 2) of std_logic_vector(2*n-1 downto 0);
signal eighthLevelRegisterInputs : array_matrix8;
signal eighthLevelRegisterOutputs: array_matrix8;

-- 2 outputs from third level of Registers
type array_matrix9 is array (0 to 1) of std_logic_vector(2*n-1 downto 0);
signal ninethLevelRegisterInputs : array_matrix9;
signal ninethLevelRegisterOutputs: array_matrix9;

signal transport_final : std_logic;

begin

-- generare produse partiale
PPGen: for i in 0 to n-1  generate
                generator: entity work.partial_product_generator port map (
                    X => X,
                    Y_bit => Y(i),
                    Partial_product => partial_products(i),
                    shift => i
                );
               end generate; -- generarea sumatoarelor elementare



FirstRegisterLevel: for i in 0 to n-1 generate
                    first_reg_output: entity work.REGISTRU 
                    generic map (n => 2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => partial_products(i),
                            output => firstLevelRegisterOutputs(i));
                    end generate;


         
FirstSSTLevel: for i in 0 to (n-2)/3 - 1 generate -- the last 2 signals will be used only on next level
                first_SST_level: entity work.SST
                    generic map (n => 2*n)
                    port map
                        (   x => firstLevelRegisterOutputs(3*i),
                            y => firstLevelRegisterOutputs(3*i + 1),
                            z => firstLevelRegisterOutputs(3*i + 2),
                            s => secondLevelRegisterInputs(2*i),
                            tout => secondLevelRegisterInputs(2*i + 1));
                end generate;



-- add last 2 registers
secondLevelRegisterInputs(20) <= firstLevelRegisterOutputs(n-2);
secondLevelRegisterInputs(21) <= firstLevelRegisterOutputs(n-1);

SecondRegisterLevel: for i in 0 to 21 generate
                second_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => secondLevelRegisterInputs(i),
                            output => secondLevelRegisterOutputs(i));
                end generate;
             
SecondSSTLevel: for i in 0 to 6 generate
                second_SST_level: entity work.SST
                    generic map (n=> 2*n)
                    port map
                        (   x => secondLevelRegisterOutputs(3*i),
                            y => secondLevelRegisterOutputs(3*i + 1),
                            z => secondLevelRegisterOutputs(3*i + 2),
                            s => thirdLevelRegisterInputs(2*i),
                            tout => thirdLevelRegisterInputs(2*i + 1));
                end generate;

-- add last register
thirdLevelRegisterInputs(14) <= secondLevelRegisterOutputs(21);                       

ThirdRegisterLevel: for i in 0 to 14 generate
                third_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => thirdLevelRegisterInputs(i),
                            output => thirdLevelRegisterOutputs(i));
                end generate;

-- now all 15 outputs from third level of registers must go in 5 sst
ThirdSSTLevel: for i in 0 to 4 generate
                third_SST_level: entity work.SST
                    generic map (n=> 2*n)
                    port map
                        (   x => thirdLevelRegisterOutputs(3*i),
                            y => thirdLevelRegisterOutputs(3*i + 1),
                            z => thirdLevelRegisterOutputs(3*i + 2),
                            s => fourthLevelRegisterInputs(2*i),
                            tout => fourthLevelRegisterInputs(2*i + 1));
                end generate;

FourthRegisterLevel: for i in 0 to 9 generate
                fourth_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => fourthLevelRegisterInputs(i),
                            output => fourthLevelRegisterOutputs(i));
                end generate;

FourthSSTLevel: for i in 0 to 2 generate
                fourth_SST_level: entity work.SST
                    generic map (n=> 2*n)
                    port map
                        (   x => fourthLevelRegisterOutputs(3*i),
                            y => fourthLevelRegisterOutputs(3*i + 1),
                            z => fourthLevelRegisterOutputs(3*i + 2),
                            s => fifthLevelRegisterInputs(2*i),
                            tout => fifthLevelRegisterInputs(2*i + 1));
                end generate;
   
fifthLevelRegisterInputs(6) <= fourthLevelRegisterOutputs(9);
   
FifthRegisterLevel: for i in 0 to 6 generate
                fifth_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => fifthLevelRegisterInputs(i),
                            output => fifthLevelRegisterOutputs(i));
                end generate;             
        
FifthSSTLevel: for i in 0 to 1 generate
                fifth_SST_level: entity work.SST
                    generic map (n=>2*n)
                    port map
                        (   x => fifthLevelRegisterOutputs(3*i),
                            y => fifthLevelRegisterOutputs(3*i + 1),
                            z => fifthLevelRegisterOutputs(3*i + 2),
                            s => sixthLevelRegisterInputs(2*i),
                            tout => sixthLevelRegisterInputs(2*i + 1));
                end generate;
                
sixthLevelRegisterInputs(4) <= fifthLevelRegisterOutputs(6);
           
SixthRegisterLevel: for i in 0 to 4 generate
                sixth_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => sixthLevelRegisterInputs(i),
                            output => sixthLevelRegisterOutputs(i));
                end generate;               
                
SixthSSTLevel: entity work.SST
                generic map (n=> 2*n)
                port map
                    (   x => sixthLevelRegisterOutputs(0),
                        y => sixthLevelRegisterOutputs(1),
                        z => sixthLevelRegisterOutputs(2),
                        s => seventhLevelRegisterInputs(0),
                        tout => seventhLevelRegisterInputs(1));      
                        
seventhLevelRegisterInputs(2) <= sixthLevelRegisterOutputs(3);
seventhLevelRegisterInputs(3) <= sixthLevelRegisterOutputs(4);
               
SeventhRegisterLevel: for i in 0 to 3 generate
                seventh_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => seventhLevelRegisterInputs(i),
                            output => seventhLevelRegisterOutputs(i));
                end generate;                
                       
SeventhSSTLevel: entity work.SST
                generic map (n=> 2*n)
                port map
                    (   x => seventhLevelRegisterOutputs(0),
                        y => seventhLevelRegisterOutputs(1),
                        z => seventhLevelRegisterOutputs(2),
                        s => eighthLevelRegisterInputs(0),
                        tout => eighthLevelRegisterInputs(1));                        

eighthLevelRegisterInputs(2) <= seventhLevelRegisterOutputs(3);
       
       
EighthRegisterLevel: for i in 0 to 2 generate
                eighth_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => eighthLevelRegisterInputs(i),
                            output => eighthLevelRegisterOutputs(i));
                end generate;       
 
EighthSSTLevel: entity work.SST
                generic map (n=> 2*n)
                port map
                    (   x => eighthLevelRegisterOutputs(0),
                        y => eighthLevelRegisterOutputs(1),
                        z => eighthLevelRegisterOutputs(2),
                        s => ninethLevelRegisterInputs(0),
                        tout => ninethLevelRegisterInputs(1)); 

NinethRegisterLevel: for i in 0 to 1 generate
                nineth_reg_level: entity work.REGISTRU
                    generic map (n=>2*n)
                    port map
                        (   clk => clk,
                            rst => rst,
                            input => ninethLevelRegisterInputs(i),
                            output => ninethLevelRegisterOutputs(i));
                end generate;   
                
--FinalSATsum: entity work.sumator_anticiparea_transportului
--        Generic map (n => n)
--        Port map( 
--        x => ninethLevelRegisterOutputs(0),
--        y => ninethLevelRegisterOutputs(1),
--        Tin => '0',
--        s => P,
--        Tout => transport_final);   

SumatorPropagareTransport: entity work.Sumator_propagarea_transportului 
                                generic map (n => 2*n)
                                port map
                                (   x => ninethLevelRegisterOutputs(0),
                                    y => ninethLevelRegisterOutputs(1),
                                    Tout => transport_final,
                                    S => P);
          
--P (63 downto 32) <= ninethLevelRegisterOutputs(1);
--P (31 downto 0) <= ninethLevelRegisterOutputs(0);
              
                
                
                                                                      
end Behavioral;
