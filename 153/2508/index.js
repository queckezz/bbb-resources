
const { writeFileSync, statSync } = require('fs')
const { resolve, join } = require('path')

const fullPath = join(process.cwd(), 'generate.sql')

const inserts = Array.from(Array(100000).keys())
  .map((_, i) => `  ('test', ${i})`)

writeFileSync(fullPath, `
use benchmark;
insert into benchmarktable (description, number) values
${inserts.join(',\n')};
`)
