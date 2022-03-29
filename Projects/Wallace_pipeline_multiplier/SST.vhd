----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/07/2016 06:18:48 PM
-- Design Name: 
-- Module Name: SST - Behavioral
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

entity SST is
  Generic( n : natural);
Port (
  x : in std_logic_vector(n-1 downto 0);
  y : in std_logic_vector(n-1 downto 0);
  z : in std_logic_vector(n-1 downto 0);
  Tout : out std_logic_vector (n-1 downto 0);
  S    : out std_logic_vector (n-1 downto 0)
 );
 
end SST;

architecture Behavioral of SST is

signal temp : std_logic_vector(n-1 downto 0) := (others=>'0');

begin

SST: entity work.Sumator_salvarea_transportului
    generic map (n => n)
    Port map(
        x => x,
        y => y,
        z => z,
        Tout => temp,
        S => S );

Tout(n-1 downto 1) <= temp(n-2 downto 0);
Tout(0) <= '0';

end Behavioral;
