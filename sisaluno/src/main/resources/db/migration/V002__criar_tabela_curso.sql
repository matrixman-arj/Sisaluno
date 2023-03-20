CREATE TABLE ensino.curso (	
	codigo serial NOT NULL,
	categoria character varying(255),
	categoria_descr character varying(100),
	cfgs_curso character varying(100),
    cfgo_curso character varying(100),
    matbel_curso character varying(100),
    oficiais_curso character varying(100),
    cpor_curso character varying(100),
    medico_curso character varying(100),
    qco_curso character varying(100),
    odonto_curso character varying(100),
	area character varying(255),
	Especialidade character varying(255),
	uete_codigo bigint,
	om_codigo bigint,
	npor_codigo bigint,
	data_inicio_curso DATE,
	data_final_curso DATE,	
	atitudinal numeric(10,2),
	atitudinal_lateral numeric(10,2),
	atitudinal_vertical numeric(10,2),
	tfm numeric(10,2),
	tfm2 numeric(10,2),
	tfm3 numeric(10,2),		
	situacao_no_curso character varying(100),	
	situacao_no_curso_descr character varying(100),	
			
	CONSTRAINT curso_pkey PRIMARY KEY (codigo)
);
	