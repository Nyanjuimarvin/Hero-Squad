--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: hero; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.hero (
    id integer NOT NULL,
    age integer,
    name character varying,
    power character varying,
    move character varying,
    weapon character varying,
    weakness character varying,
    squadid integer
);


ALTER TABLE public.hero OWNER TO access;

--
-- Name: hero_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.hero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hero_id_seq OWNER TO access;

--
-- Name: hero_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.hero_id_seq OWNED BY public.hero.id;


--
-- Name: squad; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.squad (
    id integer NOT NULL,
    name character varying,
    maxsize integer,
    cause character varying
);


ALTER TABLE public.squad OWNER TO access;

--
-- Name: squad_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.squad_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.squad_id_seq OWNER TO access;

--
-- Name: squad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.squad_id_seq OWNED BY public.squad.id;


--
-- Name: hero id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.hero ALTER COLUMN id SET DEFAULT nextval('public.hero_id_seq'::regclass);


--
-- Name: squad id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.squad ALTER COLUMN id SET DEFAULT nextval('public.squad_id_seq'::regclass);


--
-- Data for Name: hero; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.hero (id, age, name, power, move, weapon, weakness, squadid) FROM stdin;
\.


--
-- Data for Name: squad; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.squad (id, name, maxsize, cause) FROM stdin;
\.


--
-- Name: hero_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.hero_id_seq', 1, false);


--
-- Name: squad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.squad_id_seq', 1, false);


--
-- Name: hero hero_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.hero
    ADD CONSTRAINT hero_pkey PRIMARY KEY (id);


--
-- Name: squad squad_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.squad
    ADD CONSTRAINT squad_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

