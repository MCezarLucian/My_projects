----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/24/2016 11:02:11 PM
-- Design Name: 
-- Module Name: Sumator_propagarea_transportului - Behavioral
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

entity Sumator_propagarea_transportului is
  Generic (n : natural );
  Port (
        x : in std_logic_vector ( n-1 downto 0);
        y : in std_logic_vector ( n-1 downto 0);
        Tout: out std_logic;
        S : out std_logic_vector( n-1 downto 0)
   );
end Sumator_propagarea_transportului;

architecture Behavioral of Sumator_propagarea_transportului is

signal Tout_tin : std_logic_vector(n downto 0) := (others =>'0');

begin

iterate: for i in 0 to n-2  generate
            sum: entity work.sumator_elementar port map (
                x => x(i),
                y => y(i),
                Tin => Tout_tin(i),
                s => S(i),
                Tout => Tout_tin(i+1)
            );
          end generate; -- generarea sumatoarelor elementare

S(n-1) <= x(n-1) xor y(n-1) xor Tout_tin(n-1);
Tout <= Tout_tin(n);

end Behavioral;

