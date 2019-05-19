SELECT
 Name
FROM
 virus
INNER JOIN
 matchedSequences on matchedSequences.VirusID = virus.ID
WHERE
 matchedSequences.Sequence = "VMTAAASQGLTRKGVYAVRQKVNENPLYA";