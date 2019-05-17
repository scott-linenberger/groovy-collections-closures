package com.linenberger

class Rework {
	
	
	
	def fileChecklistToMap = { FileChecklist fc ->
		return fc.collectEntries {
			[
				label           : fc.label,
				id              : fc.id,
				checklistSection: fc.checklistSection.collectEntries { FileChecklistSection fcs ->
					[
							label : fcs.label,
							detail: fcs.checklistDetail.collectEntries { FileChecklistDetail fcd ->
								[
										label   : fcd.checklistDetail.label,
										id      : fcd.checklistDetail.id,
										required: fcd.checklistDetail.required
								]
							}
					]

				}
			]
		}
	}
	
	def filterRequiredChecklistDetailsOnly(Set fileChecklist) {
		
		Set filteredFileChecklist = 
			fileChecklist // work through each item in the set
				.collect(cloneFileChecklist) // convert to cloned objects
				.findAll(fileChecklistHasRequiredItems) // filter checklists w/o required items
				.collect(removeNonRequiredChecklistDetails) /* remove non-required items
				
		return filteredFileChecklist
	}
	
	/*
	 * selectively clones parts of a FileChecklist into
	 * a new object that is not connected to the data store.
	 */
	def cloneFileChecklist = { FileChecklist input ->
		/* create a new FileChecklist object */
		FileChecklist output = new FileChecklist()
		
		/* clone the checklist sections */
		Set<FileChecklistSection> checklistSection =
			input.checklistSection.collect(cloneFileChecklistSection)	
		
		/* set the cloned checklistSection on the output object */
		output.checklistSection = checklistSection	
			
		return output
	}
	
	/*
	 * selectively clones parts of a FileChecklistSection
	 * into a new object that is not connected to the data store. 
	 */
	def cloneFileChecklistSection = { FileChecklistSection input ->
		FileChecklistSection output = new FileChecklistSection()
		
		/* clone the checklist details */
		Set<FileChecklistDetail> checklistDetails = 
			input.checklistDetail.collect(cloneFileChecklistDetail)
		
		/* set the cloned checklist details on the output object */
		output.checklistDetail = checklistDetails	
			
		return output
	}
	
	/*
	 * selectively clones parts a FileChecklistDetail into a 
	 * new object that is not connected to the data store. Only
	 * ports the CheckListDetail's required property. All other
	 * properties are left blank. 
	 */
	def cloneFileChecklistDetail = { FileChecklistDetail input ->
		/* create a new object */
		FileChecklistDetail output = new FileChecklistDetail()
		
		/* rebuild the ChecklistDetail */
		ChecklistDetail checklistDetail = new ChecklistDetail()
		
		/* get the required property and set it on the new object */
		checklistDetail.required = input.checklistDetail.required
		
		/* set the checklist detail on the new object */
		output.checklistDetail = checklistDetail
		
		return output
	}
	
	/* returns true if the fileChecklist has ANY checklistSections
	 * with ChecklistDetails that have ChecklistDetail objects 
	 * where required is true
	 */
	def fileChecklistHasRequiredItems = { FileChecklist checklist ->
		def hasRequiredItems = false
		
		checklist.checklistSection.each { currentSection ->
			currentSection.checklistDetail.each { currentChecklistDetail ->
				if (currentChecklistDetail.checklistDetail == true) {
					hasRequiredItems = true
				}
			}
		}
		
		return hasRequiredItems
	}
	
	/*
	 * Iterate the checklist sections and checklist details removing 
	 * any items where the checklistDetail required property is false.
	 */
	def removeNonRequiredChecklistDetails = { FileChecklist checklist ->
		
		/* set checklistSection to the result of the collect */
		checklist.checklistSection = 
			checklist.checklistSection.collect { currentSection ->
				/* set checklistDetail to the filtered list */
				currentSection.checklistDetail = 
					currentSection.checklistDetail.findAll { currentDetail ->
						return currentDetail.checklistDetail.required // filter by required items
					}
				
				return currentSection
			}
		
		/* return the updated checklist */
		return checklist
	}
	
}
//
//	def filteredRequiredItems(Set fileChecklist) {
//		//filter checklists by required items
//		FileChecklist myChecklist = new FileChecklist();
//		println(myChecklist)
//		return fileChecklist.collect { FileChecklist currentFileChecklist ->
//			println("currentFileChecklist :" + currentFileChecklist);
//			//get fileChecklistSections
//			Set<FileChecklistSection> fileChecklistSections = currentFileChecklist.checklistSection;
//			//transform checklistSections to required items only
//			currentFileChecklist.checklistSection = fileChecklistSections.collect { FileChecklistSection currentFileChecklistSection ->
//				// filter out non-required items
//				println("checklistSections labels : " + fileChecklistSections.label + "" + fileChecklistSections);
//				currentFileChecklistSection.discard()
//				filteredRequiredItemsFromChecklistSection(currentFileChecklistSection);
//				println("currentFileChecklistSection : " + currentFileChecklistSection);
//				return currentFileChecklist;
//			}.findAll { FileChecklist filteredFileChecklist ->
//				Set<FileChecklistSection> checklistSections = filteredFileChecklist.checklistSection;
//				println("filtered checklistSection = " + checklistSections)
//				// if checklistSections is null, do not add
//				if (checklistSections == null) {
//					println("filtered out checklistSections null")
//					return false;
//				}
//				//if the list is empty do not add
//				if (checklistSections.isEmpty()) {
//					println("filtered out checklistSections empty")
//					return false;
//				}
//				Set<FileChecklistDetail> checklistDetails = checklistSections.checklistDetail.flatten().toSet();
//				// if checklistDetails is null, do not add
//				if (checklistDetails == null) {
//					println("filtered out checklistDetails null")
//					return false;
//				}
//				// if checklistDetails is empty, do not add
//				if (checklistDetails.isEmpty()) {
//					println("filtered out checklistDetails empty")
//					return false;
//				}
//				return true;
//			}
//			return currentFileChecklist;
//		};
////        return fileChecklist.collectEntries{ FileChecklist fc ->
////            [
////                    label:fc.label,
////                    id:fc.id,
////                    section: fc.checklistSection.collectEntries{FileChecklistSection fcs ->
////                        [
////                                label:fcs.label,
////                                id:fcs.id,
////                                detail:fcs.checklistDetail.collectEntries{FileChecklistDetail fcd ->
////                                    [
////                                            label:fcd.checklistDetail.label,
////                                            id:fcd.checklistDetail.id,
////                                            required:fcd.checklistDetail.required
////
////                                    ]
////                                }
////                        ]
////
////                    }
////            ]
////        }
//
//	}
//
//	def filteredRequiredItemsFromChecklistSection(FileChecklistSection fileChecklistSection){
//		// get the fileChecklistDetail from fileChecklistSection
//		Set<FileChecklistDetail> fileChecklistDetails = fileChecklistSection.checklistDetail;
//		// use findAll to filter out all non-required items
// //       fileChecklistSection.checklistDetail = fileChecklistDetails.findAll{it.checklistDetail.required}
//		fileChecklistSection.checklistDetail = fileChecklistDetails.findAll{FileChecklistDetail currentFileChecklistDetail ->
//			ChecklistDetail checklistDetail = currentFileChecklistDetail.checklistDetail;
//			println("checklist detail : " + checklistDetail.label + " required : " + checklistDetail.required)
//			return checklistDetail.required;
//		}
//	}
//
//	/**
//	 * iterates a Set of fileChecklist retreiving the fileChecklistSections,
//	 * the fileChecklistDetails and the required fileChecklistDetail items
//	 * @param fileChecklist - Set of fileChecklist to iterate
//	 * @return - none
//	 */
//	def iterateFileChecklist(Set fileChecklist){
//		fileChecklist.each {FileChecklist fileChecklists ->
//			fileChecklists.checklistSection.each {FileChecklistSection fileChecklistSection ->
//				fileChecklistSection.checklistDetail.each {FileChecklistDetail fileChecklistDetail ->
//					boolean requiredChecklistDetail = fileChecklistDetail.checklistDetail.required;
//					if(requiredChecklistDetail){
//						println("checklistDetail : " + fileChecklistDetail.checklistDetail.label + "required : " +
//								requiredChecklistDetail)
//					}
//				}
//			}
//
//
//		}

//def getRequiredFileChecklistDetails(String fileChecklistDetailId) {
//		FileChecklistDetail fcd = FileChecklistDetail.findById(fileChecklistDetailId);
//		if (fcd) {
//			File fileInstance = fcd.checklistSection.checklist.file;
//			PrintGroup printGroupInstance = fcd.checklistDetail.printGroup;
//			if (printGroupInstance) {
//				List<Long> checklistIdList = printGroupInstance.checklists.collect { it.id };
//
//				LinkedHashSet<FileChecklist> fileChecklistList = fileInstance.checklists.findAll { FileChecklist fc ->
//					return fc.checklist.id in checklistIdList;
//				}
//				iterateFileChecklist(fileChecklistList);
//				fcd.discard();
//				Set<FileChecklist> result = filteredRequiredItems(fileChecklistList);
//				println("Result : " + result)
//				return result;
//			}
//
//		}
//	}
