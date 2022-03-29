----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/23/2016 06:08:29 PM
-- Design Name: 
-- Module Name: Sumator_salvarea_transportului - Behavioral
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

entity Sumator_salvarea_transportului is
  Generic( n : natural);
  Port (
    x : in std_logic_vector(n-1 downto 0);
    y : in std_logic_vector(n-1 downto 0);
    z : in std_logic_vector(n-1 downto 0);
    Tout : out std_logic_vector (n-1 downto 0);
    S    : out std_logic_vector (n-1 downto 0)
   );
end Sumator_salvarea_transportului;

architecture Behavioral of Sumator_salvarea_transportului is

signal Tout_aux : std_logic_vector(n-1 downto 0) := (others => '0');

begin

iterate: for i in 0 to n-1  generate
            sum: entity work.sumator_elementar port map (
                x => x(i),
                y => y(i),
                Tin => z(i),
                s => S(i),
                Tout => Tout_aux(i)
            );
          end generate; -- generarea sumatoarelor elementare

Tout(n-1 downto 1) <= Tout_aux(n-2 downto 0);
Tout(0) <= '0'; -- shiftarea la stanga cu 1 bit 

end Behavioral;
