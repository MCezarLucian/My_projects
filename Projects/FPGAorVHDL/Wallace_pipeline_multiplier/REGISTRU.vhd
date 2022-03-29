----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/08/2016 11:10:15 PM
-- Design Name: 
-- Module Name: REGISTRU - Behavioral
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

entity REGISTRU is
    Generic (n : natural );
Port ( clk : in STD_LOGIC;
       rst : in STD_LOGIC;
       input : in STD_LOGIC_VECTOR (n-1 downto 0);
       output : out STD_LOGIC_VECTOR (n-1 downto 0));
end REGISTRU;

architecture Behavioral of REGISTRU is

begin

reg:    entity work.registruD
    Generic map(n => n)
    Port map(
        clk => clk,
        rst => rst,
        input => input,
        output => output);

end Behavioral;
