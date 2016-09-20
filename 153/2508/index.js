
const { writeFileSync, statSync } = require('fs')
const { resolve, join } = require('path')

const fullPath = join(process.cwd(), 'generate.sql')

const inserts = Array.from(Array(100000).keys()).map((_, i) => {
  return `INSERT into benchmarktable (description, number) values ('test', ${i});`
})

writeFileSync(fullPath, inserts.join('\n'))
