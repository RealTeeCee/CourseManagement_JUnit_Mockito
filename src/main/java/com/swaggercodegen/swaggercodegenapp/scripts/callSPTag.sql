do
$$
declare
	statuscode int;
	message character varying;
	resultset refcursor :='resultset' ;
Begin

CALL cmdb.sp_crud_tag(
	statuscode , 
	message , 
	resultset,
	0, 
	'React',
	'SELECT'
	
);
RAISE NOTICE 'status code: %',statuscode;
RAISE NOTICE 'message: %',message;
RAISE NOTICE 'resultset: %',resultset;
end;

$$;
FETCH ALL FROM resultset;