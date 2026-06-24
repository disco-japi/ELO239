# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles/Stage3_autogen.dir/AutogenUsed.txt"
  "CMakeFiles/Stage3_autogen.dir/ParseCache.txt"
  "Stage3_autogen"
  )
endif()
