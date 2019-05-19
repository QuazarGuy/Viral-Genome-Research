SELECT
 Sequence,
 count(*)
FROM
 matchedSequences
GROUP BY
 Sequence
HAVING
 count(Sequence) > 4
ORDER BY
 COUNT(*) desc;