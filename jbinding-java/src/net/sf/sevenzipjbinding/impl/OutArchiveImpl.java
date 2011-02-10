package net.sf.sevenzipjbinding.impl;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IArchiveUpdateCallback;
import net.sf.sevenzipjbinding.IOutArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

public class OutArchiveImpl implements IOutArchive {

    private long jbindingSession;
    private long sevenZipArchiveInstance;

    /**
     * Archive format enum value.
     */
    private ArchiveFormat archiveFormat;

    /**
     * 7-zip CCoders-index of the archive format {@link #archiveFormat}
     */
    private int archiveFormatIndex;

    private native void nativeUpdateItems(int archiveFormatIndex, ISequentialOutStream outStream, int numberOfItems,
            IArchiveUpdateCallback archiveUpdateCallback) throws SevenZipException;

    /**
     * {@inheritDoc}
     */
    public void updateItems(ISequentialOutStream outStream, int numberOfItems,
            IArchiveUpdateCallback archiveUpdateCallback) throws SevenZipException {
        nativeUpdateItems(archiveFormatIndex, outStream, numberOfItems, archiveUpdateCallback);
    }

    /**
     * {@inheritDoc}
     */
    public ArchiveFormat getArchiveFormat() {
        return archiveFormat;
    }

    /**
     * {@inheritDoc}
     */
    public void setSolid() throws SevenZipException {
        nativeSetSolidSpec(null);
    }

    /**
     * {@inheritDoc}
     */
    public void setSolidFiles(int countOfFilesPerBlock) throws SevenZipException {
        nativeSetSolidSpec("" + countOfFilesPerBlock + "F");
    }

    /**
     * {@inheritDoc}
     */
    public void setSolidSize(long countOfBytesPerBlock) throws SevenZipException {
        nativeSetSolidSpec("" + countOfBytesPerBlock + "B");
    }


    protected native void nativeSetLevel(int compressionLevel) throws SevenZipException;

    private native void nativeSetSolidSpec(String solidBlockSpec) throws SevenZipException;


}
