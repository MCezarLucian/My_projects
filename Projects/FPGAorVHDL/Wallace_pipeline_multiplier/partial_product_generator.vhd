----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 05/08/2016 06:07:37 PM
-- Design Name: 
-- Module Name: partial_product_generator - Behavioral
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
use ieee.numeric_std.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity partial_product_generator is
    Generic ( n : natural := 32);
    Port ( X : in STD_LOGIC_VECTOR (n-1 downto 0);
           
           Y_bit : in STD_LOGIC;
           Partial_product : out STD_LOGIC_VECTOR (2*n-1 downto 0);
           shift : in natural
           );
end partial_product_generator;

architecture Behavioral of partial_product_generator is

signal temp : std_logic_vector (n-1 downto 0);
signal temp2 : std_logic_vector (2*n-1 downto 0) := (others => '0');
signal temp3 : std_logic_vector (2*n-1 downto 0) := (others => '0');

begin

process(X, Y_bit)
begin

    for i in 0 to n-1 loop
        temp(i)<= X(i) and Y_bit;
    end loop;
    
end process;

--process(shift, temp, temp2)
--begin

  --  temp2(31 downto 0) <= temp;
    --temp2(63 downto 32) <= (others => '0');

--    if(shift /= 0) then
--        for j in 0 to shift loop
--            temp3(63 downto 1) <= temp2(62 downto 0);
--            temp3(0) <= '0';
--        end loop;
--    else
--        temp3 <= temp2;
--    end if;
--    
--end process;


    process(shift, temp)
    
    variable pos : natural := 0;

    begin
    
    pos := 32 + shift;
    
    Partial_product(63 downto pos) <= (others => '0');
    
    
    Partial_product(pos-1 downto shift) <= temp;
    
    if(shift > 0) then
        Partial_product(shift-1 downto 0) <= (others => '0');
    end if;
    
    end process;




end Behavioral;
