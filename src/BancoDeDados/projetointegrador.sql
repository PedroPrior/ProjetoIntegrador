-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06-Dez-2021 às 19:04
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projetointegrador`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_categorias`
--

CREATE TABLE `tb_categorias` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_clientes`
--

CREATE TABLE `tb_clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tb_clientes`
--

INSERT INTO `tb_clientes` (`id`, `nome`, `email`, `cpf`, `telefone`) VALUES
(1, 'PEDRO', '123@gmail.com', '   .   .   -  ', '(44) 4 4444-4444'),
(2, 'eduardo', '  eduardo@gmail.com', '321.133.223-33', '(44) 4 4444-4444');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_enderecos`
--

CREATE TABLE `tb_enderecos` (
  `id` int(11) NOT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_formapagamento`
--

CREATE TABLE `tb_formapagamento` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pedidos`
--

CREATE TABLE `tb_pedidos` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_produto` int(11) DEFAULT NULL,
  `id_formapagamento` int(11) DEFAULT NULL,
  `valor_pedido` double DEFAULT NULL,
  `id_endereco` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_produtos`
--

CREATE TABLE `tb_produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `qtd_estoque` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usuarios`
--

CREATE TABLE `tb_usuarios` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tb_usuarios`
--

INSERT INTO `tb_usuarios` (`id`, `login`, `senha`) VALUES
(1, 'adrian123', 'adrian123');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tb_categorias`
--
ALTER TABLE `tb_categorias`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tb_enderecos`
--
ALTER TABLE `tb_enderecos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Índices para tabela `tb_formapagamento`
--
ALTER TABLE `tb_formapagamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tb_pedidos`
--
ALTER TABLE `tb_pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_produto` (`id_produto`),
  ADD KEY `id_formapagamento` (`id_formapagamento`),
  ADD KEY `id_endereco` (`id_endereco`);

--
-- Índices para tabela `tb_produtos`
--
ALTER TABLE `tb_produtos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Índices para tabela `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tb_categorias`
--
ALTER TABLE `tb_categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tb_clientes`
--
ALTER TABLE `tb_clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tb_enderecos`
--
ALTER TABLE `tb_enderecos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tb_formapagamento`
--
ALTER TABLE `tb_formapagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tb_pedidos`
--
ALTER TABLE `tb_pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tb_produtos`
--
ALTER TABLE `tb_produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tb_enderecos`
--
ALTER TABLE `tb_enderecos`
  ADD CONSTRAINT `tb_enderecos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `tb_clientes` (`id`);

--
-- Limitadores para a tabela `tb_pedidos`
--
ALTER TABLE `tb_pedidos`
  ADD CONSTRAINT `tb_pedidos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `tb_clientes` (`id`),
  ADD CONSTRAINT `tb_pedidos_ibfk_2` FOREIGN KEY (`id_produto`) REFERENCES `tb_produtos` (`id`),
  ADD CONSTRAINT `tb_pedidos_ibfk_3` FOREIGN KEY (`id_formapagamento`) REFERENCES `tb_formapagamento` (`id`),
  ADD CONSTRAINT `tb_pedidos_ibfk_4` FOREIGN KEY (`id_endereco`) REFERENCES `tb_enderecos` (`id`);

--
-- Limitadores para a tabela `tb_produtos`
--
ALTER TABLE `tb_produtos`
  ADD CONSTRAINT `tb_produtos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
