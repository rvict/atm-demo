SELECT
    iu.employee_id,
    iu.username,
    iu.department,
    iu.location,
    iu.job_title
FROM test.internal_users iu
JOIN test.locations lo ON iu.location = lo.location
WHERE lo.facility_manager = 'Location Manager Alpha' 
    
UNION ALL
 
SELECT 
	eu.employee_id, 
	eu.username, 
	NULL, 
	eu.location, 
	NULL
FROM test.external_user eu
JOIN test.locations lo ON eu.location = lo.location
WHERE lo.facility_manager = 'Location Manager Alpha';